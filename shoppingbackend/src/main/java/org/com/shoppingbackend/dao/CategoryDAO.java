package org.com.shoppingbackend.dao;

import java.util.List;

import org.com.shoppingbackend.dto.Category;

public interface CategoryDAO {

	List<Category> List();
	Category get(int id);
	
}
