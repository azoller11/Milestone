package beans;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.event.Event;

public class ProductRepository{
	
	EntityManagerFactory entityManagerFactory;
	
	protected void setUp() throws Exception {
	    entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
	}
	
	public void save() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		//entityManager.persist( new Event( "Our very first event!", new Date() ) );
		//entityManager.persist( new Event( "A follow up event", new Date() ) );
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
