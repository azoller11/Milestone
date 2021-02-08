package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class SeachController {
	
	String pageSearch;
	
	public void OnSubmit(String search) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		System.out.println("You searched: " + search);
	}

}
