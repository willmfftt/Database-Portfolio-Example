package com.froyotogo;

/**
 * Represents the user table in the database
 * 
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */
public class User {

	private String username;
	private String password;
	private String userType;
	private String salt;
	private String salary;
	
	public User(String username, String password, String userType, String salt,
			String salary) {
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.salt = salt;
		this.salary = salary;
	}
	
	public User() {}

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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
}
