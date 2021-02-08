package beans;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
public class User {
	// @NotNull()
//	@Size(min=5, max=15) 
	private String firstName;
	private String lastName;
	private String Email;
	private String Address;
	private String phoneNumber;
	private String username;
	private String password;
	private boolean admin;

	public User() {
	}

	public User(String firstName, String lastName, String Email, String Address, String phoneNumber, String username,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.Email = Email;
		this.Address = Address;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
	}

	public User(String firstName, String lastName, String Email, String Address, String phoneNumber, String username,
			String password, boolean admin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.Email = Email;
		this.Address = Address;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", Email=" + Email + ", Address=" + Address
				+ ", phoneNumber=" + phoneNumber + ", username=" + username + ", password=" + password + ", admin="
				+ admin + "]";
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
