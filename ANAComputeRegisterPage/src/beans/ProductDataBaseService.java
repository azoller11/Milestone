package beans;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import business.ProductDataBaseInterface;

import java.sql.Statement;


@Stateless
@Local
@Alternative
public class ProductDataBaseService implements ProductDataBaseInterface{
	
	
	
	
	public void addProduct(Product pd) {
		System.out.println("Created a new product: " + pd.toString());
		// Connect to database
		String dbURL = "jdbc:mysql://localhost:3306/anacompute";
		String username = "root";
		String password = "root";
		Connection c = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0;
		try {
			c = DriverManager.getConnection(dbURL, username, password);
			// create a SQL statement
			stmt = c.prepareStatement(
					"insert into anacompute.product (productName, productDiscription, productAmount, productCost, imageUrl) values (?,?,?,?,?)");
			stmt.setString(1, pd.getProductName());
			stmt.setString(2, pd.getProductDiscription());
			stmt.setInt(3, pd.getProductAmount());
			stmt.setDouble(4, pd.getProductCost());
			stmt.setString(5, pd.getImageUrl());
			// execute the statemant
			rowsAffected = stmt.executeUpdate();

			// process the rows effected
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("SUCCESS!! Rows affected " + rowsAffected);
		try {
			c.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

	public ArrayList<Product> getAllProducts() {
		return this.readAllProducts();
	}
	
	
	public void decreaseOne(Product pd) {
		// Connect to database
		String dbURL = "jdbc:mysql://localhost:3306/anacompute";
		String username = "root";
		String password = "root";

		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rowsAffected = 0;
		
		try {
			//Connect to database
			c = DriverManager.getConnection(dbURL, username, password);
			
			// create a SQL statement 
			stmt = c.createStatement();
			// execute the statemant
			String prep = "update anacompute.product set productAmount = '" + (pd.getProductAmount() - 1)+"' where productID = '" + pd.getProductID() +"'" ;
			rowsAffected = stmt.executeUpdate(prep);

			System.out.println(pd.toString());
		
			
			
			
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		} finally {
			//close the connection
			try {
				stmt.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
		}
		
		
		
	}
	

	public boolean checkProduct(Product pd) {
		boolean ct = false;
		// Connect to database
		String dbURL = "jdbc:mysql://localhost:3306/anacompute";
		String username = "root";
		String password = "root";

		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Connect to database
			c = DriverManager.getConnection(dbURL, username, password);
			//System.out.println("Connection successful!");
			//System.out.println("Connected to : " + dbURL);

			// create a SQL statement
			stmt = c.createStatement();
			// execute the statemant
			rs = stmt.executeQuery("select * from anacompute.product");

			// process the rows in the result set
			while (rs.next()) {
				if (rs.getInt("productID") == pd.getProductID()) {
					if (rs.getInt("productAmount") > 0) {
						//In Stock!
						System.out.println("This item is in stock!");
						decreaseOne(pd);
					} else {
						//Out of stock :(
						System.out.println("This item is out of stock!");
					}
				}

			}
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful!");
			e.printStackTrace();
		} finally {
			// close the connection
			try {
				rs.close();
				stmt.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return ct;
		
		
	}

	public ArrayList<Product> readAllProducts() {
		// Connect to database
		String dbURL = "jdbc:mysql://localhost:3306/anacompute";
		String username = "root";
		String password = "root";

		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Product> p = new ArrayList<Product>();
		try {
			// Connect to database
			c = DriverManager.getConnection(dbURL, username, password);
			//System.out.println("Connection successful!");
		//	System.out.println("Connected to : " + dbURL);

			// create a SQL statement
			stmt = c.createStatement();
			// execute the statemant
			rs = stmt.executeQuery("select * from anacompute.product");

			// process the rows in the result set
			p.clear();
			while (rs.next()) {
				Product np = new Product();
				np.setProductID(Integer.parseInt(rs.getString("productID").toString()));
				np.setProductName(rs.getString("productName"));
				np.setProductDiscription(rs.getString("productDiscription"));
				np.setProductAmount(Integer.parseInt(rs.getString("productAmount").toString()));
				np.setProductCost(Double.parseDouble(rs.getString("productCost").toString()));
				np.setImageUrl(rs.getString("imageUrl"));
				//System.out.println(np.toString());
				p.add(np);

			}
		} catch (SQLException e) {
			//System.out.println("Connection unsuccessful!");
			e.printStackTrace();
		} finally {
			// close the connection
			try {
				rs.close();
				
				stmt.close();

				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		

		}
		
		
		return p;
	}

}
