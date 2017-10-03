package org.com.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.com.shoppingbackend.dao.CategoryDAO;
import org.com.shoppingbackend.dto.Category;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("org.com.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
		
	}
	
/*	@Test
	
	public void testAddCategory() {
		
		category = new Category();
		
		category.setName("Television");
		category.setDescription("this the some description for television");
		category.setImageURL("image2.png");
		
		assertEquals("Successfully added a category inside the table",true,categoryDAO.add(category));
		
		
		
	}
*/
	
/*	@Test
	public void testGetCategory() {
		category = categoryDAO.get(1);
		
		assertEquals("Successfully fetched a single category from the table","Men",category.getName());
		
	}
	
	*/
	
	/*	@Test
	public void testUpdateCategory() {
		category = categoryDAO.get(1);
		
		category.setName("Laptop");
		
		assertEquals("Successfully fetched a single category in the table",true,categoryDAO.update(category));
		
	}
	
	*/
	
	
	/*	@Test
	public void testDeleteCategory() {
		category = categoryDAO.get(1);
		

		
		assertEquals("Successfully deleted a single category in the table",true,categoryDAO.delete(category));
		
	}
	
	*/
	
	/*
	
		@Test
	public void testListCategory() {
	
		assertEquals("Successfully fetched a single category in the table",2,categoryDAO.list().size());
		
	}
	
	*/
	
	
	@Test
	public void testCRUDCategory() {
		
		category = new Category();
		
		category.setName("Mobiles");
		category.setDescription("this the some description for Mobiles");
		category.setImageURL("image1.png");
		
		assertEquals("Successfully added a category inside the table",true,categoryDAO.add(category));
		
		category = new Category();
		
		category.setName("Television");
		category.setDescription("this the some description for television");
		category.setImageURL("image2.png");
		
		assertEquals("Successfully added a category inside the table",true,categoryDAO.add(category));
		
		
		//fetching and updating the category
		category = categoryDAO.get(2);
		
		category.setName("Laptop");
		
		assertEquals("Successfully fetched a single category in the table",true,categoryDAO.update(category));
		
		//delete the category

		assertEquals("Successfully deleted a single category in the table",true,categoryDAO.delete(category));
		
		
		//fetching the list
		
		assertEquals("Successfully fetched a single category in the table",1,categoryDAO.list().size());
		
		
		
	}
	
	
	
	
	
	
}
