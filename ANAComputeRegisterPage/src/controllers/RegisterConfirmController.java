package controllers;


import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import business.UserBusinessServiceInterface;

@ManagedBean
public class RegisterConfirmController {
	
	public static User registerUser = new User();
    @Inject
    UserBusinessServiceInterface UDBC;
    
	public String onSubmit() {
		//Get the user value from the input form;
				FacesContext context = FacesContext.getCurrentInstance();
				User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
				
				
				
				// Send the information to the POST request
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
				System.out.println("You clicked the submit button. Sending information to the database!" + registerUser.toString());
				
				
				
					//UDBC.readAll();
					UDBC.InsertUser(registerUser);
					// TODO Auto-generated catch block
				
				
				//To open a new page after the button is hit, return the file name of the desired page
				return "Login.xhtml";
		
	}

	public String getRegisterUser() {
		String r = "";
		if (registerUser.getUsername() != null) {
			r = registerUser.getUsername();
		} else {
			r = "Not Logged In.";
		}
		return r;
	}
	
	

}
