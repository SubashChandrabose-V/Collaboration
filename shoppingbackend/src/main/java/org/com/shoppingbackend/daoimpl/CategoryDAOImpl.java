package org.com.shoppingbackend.daoimpl;
import java.util.ArrayList;
import java.util.List;

import org.com.shoppingbackend.dao.CategoryDAO;
import org.com.shoppingbackend.dto.Category;
import org.springframework.stereotype.Repository;

@Repository("categoryDAO")


public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories =new ArrayList<>();
	
	static {
		
		
		Category category=new Category();
		
		// adding category
		category.setId(1);
		category.setName("Formals");
		category.setDescription("This is some description for Formals");
		category.setImageURL("CAT_1.png");
		
		categories.add(category);
	    
		// second category
		category=new Category();
		category.setId(2);
		category.setName("casuals");
		category.setDescription("This is some description for casuals");
		category.setImageURL("CAT_2.png");
		
		categories.add(category);
		
		// third category
				category=new Category();
				category.setId(3);
				category.setName("Footwear");
				category.setDescription("This is some description for Footwear");
				category.setImageURL("CAT_3.png");
				
				categories.add(category);
				
	}
	
	
	
	@Override
	public List<Category> List() {
		// TODO Auto-generated method stub
		return categories;
	}



	@Override
	public Category get(int id) {
		
		// enchanced for loop
		for(Category category : categories) {
			
			if(category.getId() == id) return category;
		}
		return null;
		}
		
		
	}

