package controllers;

import javax.faces.bean.ManagedBean;


@ManagedBean
public class NavigationController {
	
	public String onSubmit(String desiredLocation) {
		if (getLoggedName() == null) {
			desiredLocation = "Login.xhtml";
		}
		return desiredLocation;
	}
	
	public String adminOnSubmit(String desiredLocation) {
		if (LoginController.loggedUser.isAdmin() == false) {
			desiredLocation = "Home.xhtml";
		}
		System.out.println("Send to " + desiredLocation);
		return desiredLocation;
	}
	
	
	public String getLoggedName() {
		System.out.println(LoginController.loggedUser.getUsername());
		String sendUsernName = "Not Logged In";
		if (LoginController.loggedUser.getUsername() != null && LoginController.loggedUser.getUsername() != "") {
			sendUsernName = LoginController.loggedUser.getUsername();
			return "Logged in as: " + sendUsernName;
		} 
		return sendUsernName;
	}

}
