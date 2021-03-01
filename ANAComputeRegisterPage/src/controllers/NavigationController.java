package controllers;

import javax.faces.bean.ManagedBean;

import beans.User;


@ManagedBean
public class NavigationController {
	
	public String onSubmit(String desiredLocation) {
		if (getLoggedName() == null || getLoggedName() == "Not Logged In") {
			desiredLocation = "Login.xhtml?faces-redirect=true";
		}
		return desiredLocation;
	}
	
	public String adminOnSubmit(String desiredLocation) {
		if (getLoggedUser().isAdmin() == false) {
			desiredLocation = "Home.xhtml?faces-redirect=true";
		}
		//System.out.println("Send to " + desiredLocation);
		return desiredLocation;
	}
	
	
	public String getLoggedName() {
		//System.out.println(LoginController.loggedUser.getUsername());
		String sendUsernName = "Not Logged In";
		if (LoginController.loggedUser.getUsername() != null && LoginController.loggedUser.getUsername() != "") {
			sendUsernName = LoginController.loggedUser.getUsername();
			return "Logged in as: " + sendUsernName;
		} 
		return sendUsernName;
	}
	
	public User getLoggedUser() {
		return LoginController.loggedUser;
	}

}
