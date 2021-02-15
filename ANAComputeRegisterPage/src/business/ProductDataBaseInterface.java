package business;

import java.util.ArrayList;

import javax.ejb.Local;

import beans.Product;

@Local
public interface ProductDataBaseInterface {

	public ArrayList<Product> getAllProducts();
	
	public void decreaseOne(Product pd);
	
	public boolean checkProduct(Product pd);
	
	public ArrayList<Product> readAllProducts();
	
	public void addProduct(Product pd);
	
	public void editProduct(Product pd);
	
	public void deleteProduct(Product pd);

	public Product findById(int id);
	
}
