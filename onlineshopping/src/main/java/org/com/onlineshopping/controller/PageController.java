package org.com.onlineshopping.controller;
import org.com.shoppingbackend.dao.CategoryDAO;
import org.com.shoppingbackend.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	
	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		
		//passing the list categories
		mv.addObject("categories",categoryDAO.List());
		
		
		mv.addObject("userClickHome", true);
		return mv;
	}
	@RequestMapping(value = { "about" })
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About us");
		mv.addObject("userClickAbout", true);
		return mv;
	
	}
	@RequestMapping(value = { "contact" })
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact us");
		mv.addObject("userClickContact", true);
		return mv;
	
	}
/*
 * method to load all the products and based on category
 * 
 */

	@RequestMapping(value = "/show/all/products" )
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		
		//passing the list categories
		mv.addObject("categories",categoryDAO.List());
		
		
		mv.addObject("userClickAllProducts", true);
		return mv;
	}
	@RequestMapping(value = "/show/category/{id}/products" )
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		
		
		// categoryDAO to fetch a single category
		Category category = null;
		
		category= categoryDAO.get(id);
		
		mv.addObject("title", category.getName());
		
		//passing the list categories
		mv.addObject("categories",categoryDAO.List());
		
		//passing the single category object
		mv.addObject("category",category);
		
		mv.addObject("userClickCategoryProducts",true);
		return mv;
	}

}




