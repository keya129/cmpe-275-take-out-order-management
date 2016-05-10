package com.sjsu.mvc;


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

import com.sjsu.mvc.model.Category;
import com.sjsu.mvc.model.Appetizer;
import com.sjsu.mvc.model.Desert;
import com.sjsu.mvc.model.Drink;
import com.sjsu.mvc.model.Maincourse;
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
	@ResponseBody
	public String createMenu(@RequestParam("categoryType") String categoryTpye,
			@RequestParam("name") String name,
			@RequestParam("price") String price,
			@RequestParam("calories") String calories,
			@RequestParam("prepTime") String prepTime,Model model,HttpServletRequest request) {
		
		String servingUrl = "http://www.gettyimages.pt/gi-resources/images/Homepage/Hero/PT/PT_hero_42_153645159.jpg";
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
		
		
		if (!floatOrNot(price)) {
			model.addAttribute("message", "Price is invalid");
			return "message";
		}
		if (!numberOrNot(calories)) {
			model.addAttribute("message", "Price is invalid");
			return "message";
		}
		if (!numberOrNot(prepTime)) {
			model.addAttribute("message", "Preparation time is invalid");
			return "message";
		}
		String ctype = categoryTpye;
		if(!ctype.equals("Appetizer")&&!ctype.equals("Drink")&&!ctype.equals("Desert")&&!ctype.equals("Maincourse")){
			model.addAttribute("message", "Invalid Category");
			return "message";
		}
		
		Menu menu = new Menu();
		menu.setCategoryType(categoryTpye);
		menu.setName(name);
		menu.setUrl(servingUrl);
		menu.setPrice(price);
		menu.setCalories(Integer.parseInt(calories));
		menu.setPrepTime(Integer.parseInt(prepTime));
		if (menuService.createMenu(menu)) {
			model.addAttribute("message", "Menu item added successfully");
			return "redirect:/createMenu.jsp";
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
	public String getDrinkList(Model model, HttpServletRequest request) {

		List<Entity> menu = menuService.getFullMenuList();
		
		return "message";
	}

	/****
	 * Validation Methods
	 * 
	 */
	boolean numberOrNot(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	boolean floatOrNot(String input) {
		try {
			Float.parseFloat(input);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
}