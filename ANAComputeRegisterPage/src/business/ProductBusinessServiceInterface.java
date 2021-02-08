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
	
	public ArrayList<Product> readAllProducts();
	
	public void addProduct(Product pd);


}
