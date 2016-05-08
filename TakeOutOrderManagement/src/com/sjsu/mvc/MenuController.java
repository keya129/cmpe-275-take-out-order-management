package com.sjsu.mvc;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sjsu.mvc.model.Category;
import com.sjsu.mvc.model.Appetizer;
import com.sjsu.mvc.model.Desert;
import com.sjsu.mvc.model.Drink;
import com.sjsu.mvc.model.Maincourse;
import com.sjsu.mvc.model.Profile;
import com.sjsu.mvc.service.MenuService;
import com.sjsu.mvc.service.PersonService;

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
	 * This method is for creating the Menu Item
	 */
	@RequestMapping(value = "/createMenu", params = { "categoryType", "name", "url", "price", "calories",
			"prepTime" }, method = RequestMethod.POST)
	public String createMenu(Model model, HttpServletRequest request) {
		if ((request.getParameter("url").indexOf(",")) > -1) {
			model.addAttribute("message", "Multiple URLs not allowed");
			return "message";
		}
		if (!floatOrNot(request.getParameter("price").substring(1))) {
			model.addAttribute("message", "Price is invalid");
			return "message";
		}
		if (!numberOrNot(request.getParameter("calories"))) {
			model.addAttribute("message", "Price is invalid");
			return "message";
		}
		if (!numberOrNot(request.getParameter("prepTime"))) {
			model.addAttribute("message", "Preparation time is invalid");
			return "message";
		}
		String ctype = request.getParameter("categoryType");
		switch (ctype) {
		case "Appetizer":
			Appetizer appetizer = new Appetizer();
			appetizer.setCategoryType(request.getParameter("categoryType"));
			appetizer.setName(request.getParameter("name"));
			appetizer.setUrl(request.getParameter("url"));
			appetizer.setPrice(request.getParameter("price"));
			appetizer.setCalories(Integer.parseInt(request.getParameter("calories")));
			appetizer.setPrepTime(Integer.parseInt(request.getParameter("prepTime")));
			if (menuService.createAppetizer(appetizer)) {
				model.addAttribute("message", "Menu item added successfully");
				return "message";
			} else {
				model.addAttribute("message", "Transation Failure");
				return "message";
			}
		case "Maincourse":
			Maincourse maincourse = new Maincourse();
			maincourse.setCategoryType(request.getParameter("categoryType"));
			maincourse.setName(request.getParameter("name"));
			maincourse.setUrl(request.getParameter("url"));
			maincourse.setPrice(request.getParameter("price"));
			maincourse.setCalories(Integer.parseInt(request.getParameter("calories")));
			maincourse.setPrepTime(Integer.parseInt(request.getParameter("prepTime")));
			if (menuService.createMaincourse(maincourse)) {
				model.addAttribute("message", "Menu item added successfully");
				return "message";
			} else {
				model.addAttribute("message", "Transation Failure");
				return "message";
			}
		case "Drink":
			Drink drink = new Drink();
			drink.setCategoryType(request.getParameter("categoryType"));
			drink.setName(request.getParameter("name"));
			drink.setUrl(request.getParameter("url"));
			drink.setPrice(request.getParameter("price"));
			drink.setCalories(Integer.parseInt(request.getParameter("calories")));
			drink.setPrepTime(Integer.parseInt(request.getParameter("prepTime")));
			if (menuService.createDrink(drink)) {
				model.addAttribute("message", "Menu item added successfully");
				return "message";
			} else {
				model.addAttribute("message", "Transation Failure");
				return "message";
			}
		case "Desert":
			Desert desert = new Desert();
			desert.setCategoryType(request.getParameter("categoryType"));
			desert.setName(request.getParameter("name"));
			desert.setUrl(request.getParameter("url"));
			desert.setPrice(request.getParameter("price"));
			desert.setCalories(Integer.parseInt(request.getParameter("calories")));
			desert.setPrepTime(Integer.parseInt(request.getParameter("prepTime")));
			if (menuService.createDesert(desert)) {
				model.addAttribute("message", "Menu item added successfully");
				return "message";
			} else {
				model.addAttribute("message", "Transation Failure");
				return "message";
			}
		default:
			model.addAttribute("message", "Invalid Category Selected");
			return "message";
		}
	}

	@RequestMapping(value = "/deleteMenu", params = { "categoryType", "id" }, method = RequestMethod.POST)
	public String deleteMenuItem(Model model, HttpServletRequest request) {
		if (menuService.deleteMenu(Integer.parseInt(request.getParameter("id")),
				request.getParameter("categoryType"))) {
			model.addAttribute("message", "Menu item deleted successfully");
			return "message";
		} else {
			model.addAttribute("message", "Transation Failure");
			return "message";
		}
	}

	@RequestMapping(value = "/enableMenu", params = { "categoryType", "id" }, method = RequestMethod.POST)
	public String enableMenuItem(Model model, HttpServletRequest request) {
		if (menuService.enableMenu(Integer.parseInt(request.getParameter("id")),
				request.getParameter("categoryType"))) {
			model.addAttribute("message", "Menu item deleted successfully");
			return "message";
		} else {
			model.addAttribute("message", "Transation Failure");
			return "message";
		}
	}

	@RequestMapping(value = "/getAppetizerList", method = RequestMethod.GET)
	public String getAppetizerList(Model model, HttpServletRequest request) {

		List<Entity> appetizer = menuService.getAppetizerList();
		model.addAttribute("appetizerList", appetizer);
		return "message";
	}

	@RequestMapping(value = "/getDesertList", method = RequestMethod.GET)
	public String getDesertList(Model model, HttpServletRequest request) {

		List<Entity> desert = menuService.getDesertList();
		model.addAttribute("desertList", desert);
		return "message";
	}

	@RequestMapping(value = "/getDrinkList", method = RequestMethod.GET)
	public String getDrinkList(Model model, HttpServletRequest request) {

		List<Entity> drink = menuService.getDrinkList();
		model.addAttribute("drinkList", drink);
		return "message";
	}

	@RequestMapping(value = "/getMaincourseList", method = RequestMethod.GET)
	public String getMaincourse(Model model, HttpServletRequest request) {

		List<Entity> maincourse = menuService.getMaincourseList();
		model.addAttribute("maincourseList", maincourse);
		return "message";
	}

	@RequestMapping(value = "/getDisabledMenuList", method = RequestMethod.GET)
	public String getDisabledMenuList(Model model, HttpServletRequest request) {

		List<Entity> disabledMenu = menuService.getDisabledMenuList();
		model.addAttribute("disabledMenuList", disabledMenu);
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