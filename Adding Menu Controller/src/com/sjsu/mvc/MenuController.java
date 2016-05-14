package com.sjsu.mvc;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
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
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;


@Controller
public class MenuController {

	private MenuService menuService;
	 private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	 
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
	 * @throws IOException 
	 * @throws FileUploadException 
	 */
	
	@RequestMapping(value = "/createMenu", method = RequestMethod.POST)
	public String createMenu(
			Model model,HttpServletRequest request,
			HttpServletResponse response) throws IOException, FileUploadException {

    	System.out.println("Here");
    	
    	
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
        List<BlobKey> blobKeys = blobs.get("myFile");
        String image = null;
        if (blobKeys == null || blobKeys.isEmpty()) {
        	model.addAttribute("message", "Image null");
        	request.getSession().setAttribute("message", "Image null");
			return "message";
        } else {
        	 image = blobKeys.get(0).getKeyString();
            System.out.println("The blob received" + blobKeys.get(0).getKeyString());
        }
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		long calories = Long.parseLong(request.getParameter("calories"));
		
		long preptime = Long.parseLong(request.getParameter("preptime").trim());
		
      
		
		if(!category.equals("Appetizer")&&!category.equals("Drink")&&!category.equals("Desert")&&!category.equals("Maincourse")){
			model.addAttribute("message", "Invalid Category");
			request.getSession().setAttribute("message", "Invalid Category");
			return "message";
		}
		
		java.util.Date date= new java.util.Date();
		
		Menu menu = new Menu();
		menu.setCategory(category);
		menu.setName(name);
		menu.setPicture(image);
		menu.setPrice(price);
		menu.setCalories(calories);
		menu.setPreptime(preptime);
		menu.setMstatus(true);
		menu.setCreated(new Timestamp(date.getTime()));
		menu.setTemprating(0);
		menu.setFinalrating(0);
		
		if (menuService.createMenu(menu)) {
			model.addAttribute("message", "Menu item added successfully");
	        request.getSession().setAttribute("message", "Menu item added successfully");

			return "message";
		} else {
			model.addAttribute("message", "Transaction Failure");
			request.getSession().setAttribute("message", "Transaction Failure");
			return "message";
		}
        
		
	}

	@RequestMapping(value = "/deleteMenu", params = { "id" }, method = RequestMethod.POST )
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

		List<Menu> menu = this.menuService.getMenuList();
		
		model.addAttribute("message", menu);
		return "message";
	}

}