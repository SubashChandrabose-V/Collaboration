package org.com.onlineshopping.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.com.onlineshopping.util.FileUploadUtility;
import org.com.onlineshopping.validator.ProductValidator;
import org.com.shoppingbackend.dao.CategoryDAO;
import org.com.shoppingbackend.dao.ProductDAO;
import org.com.shoppingbackend.dto.Category;
import org.com.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

		@Autowired
		private CategoryDAO categoryDAO;
		
		@Autowired
		private ProductDAO productDAO;
		
		private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation) {
		ModelAndView mv= new ModelAndView("page");
		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		Product nProduct = new Product();
		//set few of the fields
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product", nProduct);
		
		
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message","Product Submitted Successfully");
			}
			else if(operation.equals("category")) {
				mv.addObject("message", "Category Submitted Succesfully");
			}
		}
		
		return mv;
	}
	
	
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		//fetch the product from the database
		Product nProduct = productDAO.get(id);
		//set the product fetch from the database
		
		mv.addObject("product", nProduct);
		
		return mv;
	}
	
	
	
	
	
	
	//handling product submission
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model, HttpServletRequest request)
	{
		// handle image validation for new Products
		if(mProduct.getId() == 0) {
			
		
		new ProductValidator().validate(mProduct, results);
		}
		else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}
		
		// check if there are any errors 
		if(results.hasErrors()) {
			
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message","Validation failed for Product Submission!");
			return "page";
		}
		
		logger.info(mProduct.toString());
		//create new product
		
		if(mProduct.getId()==0)
		{
			// create a new product record if id is o
			productDAO.add(mProduct);
			
		}
		else {
			//update the product if id is not 0
			productDAO.update(mProduct);
			
		}
		
		
		if(!mProduct.getFile().getOriginalFilename().equals(""))
		{
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	
	@RequestMapping(value = "/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		
		//is going to fetch the product from the database
		Product product = productDAO.get(id);
		
		boolean isActive = product.isActive();
		//activating and deactivating based on the value of active field
		product.setActive(!product.isActive());
		//updating the product
		productDAO.update(product);
		
		
		return (isActive)? " You have sucessfully deactivated the product with id" + product.getId()
							:"You have sucessfully activated the product with id" + product.getId();
	}
	
	
	
	// to handle category submission
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		//add new category
		categoryDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	
	
	
	
	//returing categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		
		return categoryDAO.list();
	}

	
	@ModelAttribute("category") 
	public Category getCategory() {
		return new Category();
	}
	
	
}