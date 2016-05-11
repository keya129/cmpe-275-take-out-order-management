package com.sjsu.mvc;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sjsu.mvc.model.Menu;
import com.sjsu.mvc.model.Profile;
import com.sjsu.mvc.service.MenuService;
import com.sjsu.mvc.service.PersonService;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private MenuService menuService;

	@Autowired(required = true)
	@Qualifier(value = "menuService")
	public void setMenuService(MenuService ms) {
		this.menuService = ms;
	}

	/**
	 * This method is for Menu Form
	 */
	@RequestMapping(value = "/displayMenu", method = { RequestMethod.GET })
	public String login() {
		return "createMenu";

	}

	/**
	 * This method is for creating the Menu Item
	 */
	
	@RequestMapping(value = "/createMenu", method = RequestMethod.POST)
	public String createMenu(@RequestParam("category") String category,
			@RequestParam("name") String name,
			@RequestParam("price") double price,
			@RequestParam("calories") int calories,
			@RequestParam("preptime") int preptime,
			Model model,HttpServletRequest request) {
		System.out.println("category " +category);

		System.out.println("name " +name);
		System.out.println("price " +price);
		System.out.println("calories "+calories);
		System.out.println("preptime "+preptime);

		String servingUrl;
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		@SuppressWarnings("deprecation")
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
		BlobKey blobKey = blobs.get("menuImage");
		
		if (blobKey == null) {
			model.addAttribute("message", "No Image Upload");
			return "message";
		}else{
			servingUrl = blobKey.getKeyString();
		}
		
		
		if(!category.equals("Appetizer")&&!category.equals("Drink")&&!category.equals("Desert")&&!category.equals("Maincourse")){
			model.addAttribute("message", "Invalid Category");
			return "message";
		}
		
		java.util.Date date= new java.util.Date();
		
		Menu menu = new Menu();
		menu.setCategory(category);
		menu.setName(name);
		menu.setPicture(servingUrl);
		menu.setPrice(price);
		menu.setCalories(calories);
		menu.setPreptime(preptime);
		menu.setMstatus(true);
		menu.setCreated(new Timestamp(date.getTime()));
		menu.setTemprating(0);
		menu.setFinalrating(0);
		
		if (menuService.createMenu(menu)) {
			model.addAttribute("message", "Menu item added successfully");
			return "message";
		} else {
			model.addAttribute("message", "Transaction Failure");
			return "message";
		}
		
	}

	@RequestMapping(value = "/deleteMenu", params = { "id" }, method = RequestMethod.POST)
	public String deleteMenuItem(Model model, HttpServletRequest request) {
		if (menuService.deleteMenu(Integer.parseInt(request.getParameter("id")))) {
			model.addAttribute("message", "Menu item deleted successfully");
			return "message";
		} else {
			model.addAttribute("message", "Transaction Failure");
			return "message";
		}
	}

	@RequestMapping(value = "/enableMenu", params = { "id" }, method = RequestMethod.POST)
	public String enableMenuItem(Model model, HttpServletRequest request) {
		if (menuService.enableMenu(Integer.parseInt(request.getParameter("id")))) {
			model.addAttribute("message", "Menu item deleted successfully");
			return "message";
		} else {
			model.addAttribute("message", "Transaction Failure");
			return "message";
		}
	}

	@RequestMapping(value = "/getMenuList", method = RequestMethod.GET)
	public String getMenuList(Model model, HttpServletRequest request) {

		List<Menu> menu = menuService.getMenuList();
		
		model.addAttribute("message", menu);
		return "message";
	}

}