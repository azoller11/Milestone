package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.NamingContainer;
import javax.faces.context.ExternalContext;
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

	public NavigationController NC = new NavigationController();

	public String search = "";
	public Product viewProduct = new Product();

	ArrayList<Product> productList = new ArrayList<Product>();

	/*
	 * Redirect to page,
	 * 
	 * Instead of: return "Home.xhtml";
	 * 
	 * Use: return "Home.xhtml?faces-redirect=true";
	 * 
	 */

	public String CreateNewProduct() {
		String returnPage = "Home.xhtml";
		boolean check = false;
		// Get the Product value from the input form;
		FacesContext context = FacesContext.getCurrentInstance();
		Product product = context.getApplication().evaluateExpressionGet(context, "#{product}", Product.class);
		if (product.getProductName() == null || product.getProductName() == "") {
			check = true;
		}
		if (product.getProductCost() == 0) {
			check = true;
		}
		if (product.getProductDiscription() == "" || product.getProductDiscription() == null) {
			check = true;
		}
		// System.out.println("Here is the image:" + product.getImageUrl());
		if (product.getImageUrl() == null || !product.getImageUrl().contains(".")) {
			product.setImageUrl(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8uAhGGI22hpBLoEYk-d9Hb4puo6MM8KGzKA&usqp=CAU");
			// System.out.println("Here is the image:" + product.getImageUrl());
		}

		if (check) {
			returnPage = "CreateProduct.xhtml";
		} else {
			PDBC.addProduct(product);
		}

		return returnPage;
	}

	@SessionScoped
	public String purchase(Product c) throws SQLException {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("product", c);
		String result = "Home.xhtml?faces-redirect=true";
		// System.out.println("You clicked the submit button. Sending information to the
		// database!" + c.toString());
		// Checks to see if the user is logged in:
		if (NC.getLoggedName() == null || NC.getLoggedName() == "Not Logged In") {
			result = "Login.xhtml?faces-redirect=true";
		} else {
			// Since they are logged in, lets check if the item is availible, if so add to
			// their cart;
			if (PDBC.checkProduct(c)) {
				shoppingCart.add(c);
			}
		}

		return result;
	}

	public void searchProducts() {
		productList.clear();
		// search = showResult();
		for (Product ap : PDBC.getAllProducts()) {
			boolean includes = false;
			if (ap.getProductName().contains(search)) {
				includes = true;
			}
			if (ap.getProductDiscription().contains(search)) {
				includes = true;
			}
			if (ap.getImageUrl().contains(search)) {
				includes = true;
			}
			if (includes) {
				productList.add(ap);
			}
		}
		// if there is no items in returned, return it allllllll;
		if (productList.size() <= 0 || search == null) {
			productList = PDBC.getAllProducts();
		}

	}

	public String executeSearch(String search) {
		if (search != "" || search != null) {
			setSearch(search);
		}
		searchProducts();
		return "Home.xhtml?faces-redirect=true";
	}

	public ArrayList<Product> getProductList() {
		if (productList.size() <= 0 || search == null) {
			productList = PDBC.getAllProducts();
		}
		// Collections.shuffle(productList);
		return productList;
	}

	public ArrayList<Product> getShoppingCart() {
		return shoppingCart;
	}

	public ProductBusinessServiceInterface getPDBC() {
		return PDBC;
	}

	public void setSearch(String search) {
		if (search != "" || search != null) {
			this.search = search;
		}

	}

	public String getSearch() {
		return search;
	}

	public String edit(Product c) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("product", c);
		String result = "Edit.xhtml";
		// System.out.println(c.toString());
		if (!LoginController.loggedUser.isAdmin()) {
			result = "Products.xhtml?faces-redirect=true";
		}
		return result;
	}

	public String view(Product c) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("product", c);
		String result = "ViewProduct.xhtml";
		// System.out.println(c.toString());
		return result;
	}

	public String saveEdit() {
		FacesContext context = FacesContext.getCurrentInstance();
		Product editedProduct = context.getApplication().evaluateExpressionGet(context, "#{product}", Product.class);
		// System.out.println("This is the edited information post: " +
		// editedProduct.toString());
		PDBC.editProduct(editedProduct);
		return "Products.xhtml?faces-redirect=true";
	}

	public String deleteProduct() {
		FacesContext context = FacesContext.getCurrentInstance();
		Product deleteProduct = context.getApplication().evaluateExpressionGet(context, "#{product}", Product.class);
		PDBC.deleteProduct(deleteProduct);
		return "Products.xhtml?faces-redirect=true";
	}

}
