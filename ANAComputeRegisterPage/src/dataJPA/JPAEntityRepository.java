package dataJPA;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import beans.Product;
import business.ProductBusinessServiceInterface;

public class JPAEntityRepository {

	public static final String PERSISTENCE_UNIT = "db";
	private static EntityManager em;
	@Inject
	ProductBusinessServiceInterface PDBC;

	public static void listAll() {
		Query queryAll = em.createNamedQuery("Product.findAll");
		List<Product> products = queryAll.getResultList();
		for (int i = 0; i < products.size(); i++) {
			System.out.println("===========================");
			Product product = products.get(i);
			System.out.println(product.toString());
			System.out.println("===========================");
		}
	}

}
