package com.froyotogo;

/**
 * Represents the customer table of the database
 * 
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */

public class Customer {

	private String firstName;
	private String lastName;
	private String address;
	private String phoneNum;
	private String email;
	private String birthday;

	public Customer(String firstName, String lastName, String address,
			String phoneNum, String email, String birthday) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNum = phoneNum;
		this.email = email;
		this.birthday = birthday;

	}
	
	public Customer() {}

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

	public String getAddress() {
		
		return address;
		
	}

	public void setAddress(String address) {
		
		this.address = address;
		
	}

	public String getPhoneNum() {
		
		return phoneNum;
		
	}

	public void setPhoneNum(String phoneNum) {
		
		this.phoneNum = phoneNum;
		
	}

	public String getEmail() {
		
		return email;
		
	}

	public void setEmail(String email) {
		
		this.email = email;
		
	}

	public String getBirthday() {
		
		return birthday;
		
	}

	public void setBirthday(String birthday) {
		
		this.birthday = birthday;
		
	}

}
