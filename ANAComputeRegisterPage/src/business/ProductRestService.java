package business;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import beans.Product;

@RequestScoped
@Path("/products")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ProductRestService {

	@Inject
	ProductDataBaseInterface db;
	
	/*
	 * This section receives data based on a string given, maybe everything, if it
	 * fits the name
	 */

	@GET
	@Path("/getjsonbyname/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Product> getOneProductAsJson(@PathParam("name") String name) {
		return db.searchFor(name);
	}

	@GET
	@Path("/getxmlbyname/{name}")
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Product> getOneOrdersAsXml(@PathParam("name") String name) {
		return db.searchFor(name);

	}

	/*
	 * This section receives desired data, not everything
	 */

	@GET
	@Path("/getjsonbyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getOneProductAsJson(@PathParam("id") int id) {
		return db.findById(id);
	}

	@GET
	@Path("/getxmlbyid/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Product getOneOrdersAsXml(@PathParam("id") int id) {
		return db.findById(id);

	}

	/*
	 * This section receives all data and formats it into either JSON or XML
	 * respectively;
	 */

	@GET
	@Path("/getjson")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductsAsJson() {
		return db.getAllProducts();
	}

	@GET
	@Path("/getxml")
	@Produces(MediaType.APPLICATION_XML)
	public Product[] getOrdersAsXml() {
		List<Product> products = db.getAllProducts();
		return products.toArray(new Product[products.size()]);

	}

}
