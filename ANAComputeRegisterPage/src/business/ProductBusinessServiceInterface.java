package business;

import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Product;

@Stateless
@Local
@Alternative

public interface ProductBusinessServiceInterface {
	
	public ArrayList<Product> getAllProducts();
	
	public void decreaseOne(Product pd);
	
	public boolean checkProduct(Product pd);
	
	public void addProduct(Product pd);
	
	//To Comply with CRUD we need (find by id, find all, update, and delete methods of CRUD) 
	
	public void editProduct(Product pd); //Update
	
	public void deleteProduct(Product pd); //Delete
	
	public ArrayList<Product> readAllProducts(); // Find by ID... But, could we just use this and then check if the returned list has the desired product? if (gottenProdect().getID() == ID)

	public Product findById(int id);
	
	public ArrayList<Product> searchFor(String name);

}
