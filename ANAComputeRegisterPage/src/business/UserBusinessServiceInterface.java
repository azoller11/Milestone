package business;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.User;


@Stateless
@Local
@Alternative
public interface UserBusinessServiceInterface {

	
	public void InsertUser(User user);
	
	public boolean check(String usName, String usPassword);
	
	public void readAll();
	
	public void deleteUser(User user);
	
	public User getUserInformation(String username);
	
}
