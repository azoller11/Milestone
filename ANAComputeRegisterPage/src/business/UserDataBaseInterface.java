package business;

import javax.ejb.Local;

import beans.User;

@Local
public interface UserDataBaseInterface {
	
	public void InsertUser(User user);
	
	public boolean check(String usName, String usPassword);
	
	public void readAll();
	
	public void deleteUser(User user);
	

}
