package business;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import beans.User;

@Local
@Stateless
@Alternative
public class UserBusinessService implements UserBusinessServiceInterface{
	
	@Inject
	UserDataBaseInterface db;

	@Override
	public void InsertUser(User user) {
	db.InsertUser(user);
		
	}

	@Override
	public boolean check(String usName, String usPassword) {
		return db.check(usName, usPassword);
	}

	@Override
	public void readAll() {
		db.readAll();
		
	}

	@Override
	public void deleteUser(User user) {
		db.deleteUser(user);
		
	}

	@Override
	public User getUserInformation(String username) {
		return db.getUserInformation(username);
	}

}
