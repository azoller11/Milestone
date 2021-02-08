package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Product;
import beans.ProductDataBaseService;
import beans.User;
import business.ProductBusinessServiceInterface;

@ManagedBean
public class StoreManager {
	
	
	public ArrayList<Product> shoppingCart = new ArrayList<Product>();
	
	@Inject
	ProductBusinessServiceInterface PDBC;
	
	public String enterValidInfo = "Please enter valid info";
	
	
	public String CreateNewProduct() {
		String returnPage = "Home.xhtml";
		boolean check = false;
		//Get the Product value from the input form;
		FacesContext context = FacesContext.getCurrentInstance();
		Product product = context.getApplication().evaluateExpressionGet(context, "#{product}", Product.class);
		if(product.getProductName() == null || product.getProductName() == "") {
			check = true;
			System.out.println("FAIL@@!!!!");
		}
		if (product.getProductCost() == 0) {
			System.out.println("FAIL@@!!!!");
			check = true;
		}
		if (product.getProductDiscription() == "" || product.getProductDiscription() == null) {
			System.out.println("FAIL@@!!!!");
			check = true;
		}
		if (product.getImageUrl() == null) {
			product.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8uAhGGI22hpBLoEYk-d9Hb4puo6MM8KGzKA&usqp=CAU");
		}
		
		
		
		if (check) {
			returnPage = "CreateProduct.xhtml";
		} else {
			PDBC.addProduct(product);
		}
		
		return returnPage;
	}
	
	@SessionScoped
	public void purchase(Product c) throws SQLException {
		System.out.println("You clicked the submit button. Sending information to the database!" + c.toString());
		if (PDBC.checkProduct(c)) {
			shoppingCart.add(c);
		}
		
		
	}
	
	public ArrayList<Product> getAllProducts() {
		return PDBC.readAllProducts();
	}
	
	
	
	
	
	public String edit(Product c) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("product", c);
		String result = "Edit.xhtml";
		if (!LoginController.loggedUser.isAdmin()) {
			result = "Home.xhtml";
		}
		return result;
	}
	
	

}
