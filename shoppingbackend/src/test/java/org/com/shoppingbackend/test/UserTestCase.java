package org.com.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.com.shoppingbackend.dao.UserDAO;
import org.com.shoppingbackend.dto.Address;
import org.com.shoppingbackend.dto.Cart;
import org.com.shoppingbackend.dto.User;

public class UserTestCase {
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("org.com.shoppingbackend");
		context.refresh();
		
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	/*@Test
	public void testAdd() {
		
		user = new User();
		user.setFirstName("Hemadri");
		user.setLastName("yadav");
		user.setEmail("hemadriyadav@gmail.com");
		user.setContactNumber("12345678");
		user.setRole("USER");
		user.setPassword("123456");
		
		// add the user
		
		assertEquals("Failed to add user!", true, userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("6/49 chidas spining mill opp");
		address.setAddressLineTwo("Narayanavanam road puttur");
		address.setCity("puttur");
		address.setState("AP");
		address.setCountry("India");
		address.setPostalCode("517583");
		address.setBilling(true);
		
		// link the user with the address user id
		
		address.setUserId(user.getId());
		// add the address
		
		assertEquals("Failed to add address!", true, userDAO.addAddress(address));
		
		if(user.getRole().equals("USER")) {
			
			// create a cart for this user
			
			cart = new Cart();
			
			cart.setUser(user);
			
			//add the cart
			assertEquals("Failed to add cart!",true, userDAO.addCart(cart));
			
			// add a shipping address for this year
			
			address = new Address();
			address.setAddressLineOne("6/49 chidas spining mill opp");
			address.setAddressLineTwo("Narayanavanam road puttur");
			address.setCity("puttur");
			address.setState("AP");
			address.setCountry("India");
			address.setPostalCode("517583");
			//set shipping to true
			address.setShipping(true);
			
			// link with the user
			address.setUserId(user.getId());
			
			// add the shipping address
			assertEquals("Failed to add shipping address!", true,userDAO.addAddress(address));
			
		}
		
	}
	*/
	
/*	@Test
	public void testAdd() {
		user = new User();
		user.setFirstName("Hemadri");
		user.setLastName("yadav");
		user.setEmail("hemadriyadav@gmail.com");
		user.setContactNumber("12345678");
		user.setRole("USER");
		user.setPassword("123456");
		
		
		if(user.getRole().equals("USER")) {
			// create a cart for this user
			cart = new Cart();
			
			cart.setUser(user);
			
			// attach cart with the user
			user.setCart(cart);
		}
		
		// add the user
		assertEquals("Failed to add user!",true, userDAO.addUser(user));
		
	}
	*/
/*	
	@Test
	public void testUpdateCart() {
		
		//fetch the user by its email
		
		user = userDAO.getByEmail("hemadriyadav@gmail.com");
		
		// get the cart of the user
		
		cart = user.getCart();
		
		cart.setGrandTotal(5555);
		
		cart.setCartLines(2);
		
		assertEquals("Failed to update the cart!", true, userDAO.updateCart(cart));
		
	}
	*/
	
	
	/*@Test
	public void testAddAddress() {
		// we need to add an user
		user = new User();
		user.setFirstName("Hemadri");
		user.setLastName("yadav");
		user.setEmail("hemadriyadav@gmail.com");
		user.setContactNumber("12345678");
		user.setRole("USER");
		user.setPassword("123456");
		
		// add the user
		
		assertEquals("Failed to add user!", true, userDAO.addUser(user));
		// we are going to add the address
		
		
		address = new Address();
		address.setAddressLineOne("6/49 chidas spining mill opp");
		address.setAddressLineTwo("Narayanavanam road puttur");
		address.setCity("puttur");
		address.setState("AP");
		address.setCountry("India");
		address.setPostalCode("517583");
		address.setBilling(true);
		
		// attached the user to the address
		
		address.setUser(user);
		assertEquals("Failed to add address!",true, userDAO.addAddress(address));
		
		// we are also going to shipping address
		

		address = new Address();
		address.setAddressLineOne("6/49 chidas spining mill opp");
		address.setAddressLineTwo("Narayanavanam road puttur");
		address.setCity("puttur");
		address.setState("AP");
		address.setCountry("India");
		address.setPostalCode("517583");
		//set shipping to true
		address.setShipping(true);
		
		// attached the user to the address
		
				address.setUser(user);
				assertEquals("Failed to add shipping address!",true, userDAO.addAddress(address));
				
		
	}*/
	
	/*@Test
	public void testAddAddress() {
		
		user = userDAO.getByEmail("hemadriyadav@gmail.com");
		
		// we are also going to shipping address
		
				address = new Address();
				address.setAddressLineOne("6/49 chidas spining mill opp");
				address.setAddressLineTwo("Narayanavanam");
				address.setCity("tpt");
				address.setState("AP");
				address.setCountry("India");
				address.setPostalCode("517500");
				//set shipping to true
				address.setShipping(true);
				
				// attached the user to the address
				
						address.setUser(user);
						assertEquals("Failed to add shipping address!",true, userDAO.addAddress(address));
						
		
	}*/
	
	@Test
	public void testGetAddresses() {
		

		user = userDAO.getByEmail("hemadriyadav@gmail.com");
		
		assertEquals("Failed to fetch the list of Address and Size does not match!",2,
				userDAO.listShippingAddresses(user).size());
		
		assertEquals("Failed to fetch the list the billing address and Size does not match!","puttur",
				userDAO.getBillingAddress(user).getCity());
	}
	
	
	
}

