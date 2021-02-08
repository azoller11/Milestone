package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import beans.User;

@ManagedBean
public class FormController {
	
	public User fix = new User("*", "*", "*", "*", "*", "*", "*", false);
	
	public String onSubmit() {
		String responsePage = "Response.xhtml";
		boolean check = false;
		//Get the user value from the input form;
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		System.out.println("You clicked the submit button: " + user.toString());
		
		// Send the information to the POST request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		
		if (user.getFirstName() == null || user.getFirstName() == "" || user.getFirstName().length() < 4) {
			//Need to fix firstname;
			fix.setFirstName("Enter a valid first name");
			check = true;
		} else {
			fix.setFirstName("*");
		}
		if (user.getLastName() == null || user.getLastName() == "" || user.getLastName().length() < 4) {
			fix.setLastName("Enter a valid last name");
			//Need to fix lastname;
			check = true;
		} else {
			fix.setLastName("*");
		}
		if (user.getEmail() == null || user.getEmail() == "" || !user.getEmail().contains("@") || !user.getEmail().contains(".")) {
			//Need to fix email;
			fix.setEmail("Enter a valid Email");
			check = true;
		} else {
			fix.setEmail("*");
		}
		if (user.getAddress() == null || user.getAddress() == "" || user.getAddress().length() < 5) {
			//Need to fix address;
			fix.setAddress("Enter a valid address");
			check = true;
		} else {
			fix.setAddress("*");
		}
		if (user.getPhoneNumber() == null || user.getPhoneNumber() == "" || user.getPhoneNumber().length() < 8) {
			//Need to fix phonenumber;
			fix.setPhoneNumber("Enter a vaild password");
			check = true;
		} else {
			fix.setPhoneNumber("*");
		}
		if (user.getUsername() == null || user.getUsername() == "" || user.getUsername().length() < 4) {
			//Need to fix username;
			fix.setUsername("Enter a vaild username");
			check = true;
		} else {
			fix.setUsername("*");
		}
		if (user.getPassword() == null || user.getPassword() == "" || user.getPassword().length() < 4) {
			//Need to fix password;
			fix.setPassword("Enter a vaild password");
			check = true;
		} else {
			fix.setPassword("*");
		}
		
		
		
		
		
		//if check is true, send back to register page to finish filling out the required fields;
		if (check) {
			RegisterConfirmController.registerUser = user;
			responsePage = "CreateAccount.xhtml";
		}
		
		//Send user info to the confirm page;
		RegisterConfirmController.registerUser = user;
		
		//To open a new page after the button is hit, return the file name of the desired page
		return responsePage;
	}

	
	public User getUser() {
		return fix;
	}
	
	

}
