package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class SeachController {
	
	String pageSearch;
	
	public void OnSubmit() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		System.out.println("You searched: " + context.toString());
	}

}
