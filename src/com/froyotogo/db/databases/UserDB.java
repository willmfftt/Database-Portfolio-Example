package com.froyotogo.db.databases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.froyotogo.User;
import com.froyotogo.db.CRUD;
import com.froyotogo.db.DBConnection;

/**
 * Represents the user table in the database, with all the CRUD functions
 * 
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */

public class UserDB extends Database {
	
	public static final String TABLE_NAME = "userInfo900650079";
	public static final String USERNAME = "userid";
	public static final String PASSWORD = "pass";
	public static final String SALT = "salt";
	public static final String TYPE = "userType";
	public static final String SALARY = "salary";
	
	public UserDB(DBConnection connect) {
		
		super(connect);
		
	}
	
	@Override
	public void createDB() {
		
		String sql = "CREATE TABLE IF NOT EXISTS " + DB_NAME + "." + TABLE_NAME
				+ " (" + USERNAME + " VARCHAR(255) NOT NULL PRIMARY KEY, "
				+ PASSWORD + " VARCHAR(100) NOT NULL, "
				+ SALT + " TINYINT(3) NOT NULL, "
				+ TYPE + " CHAR(1) NOT NULL, "
				+ SALARY + " DECIMAL(10,2))";
		
		Connection connection = connect.connect();		
		CRUD crud = new CRUD(connection);
		
		boolean success = crud.create(sql);
		
		if (success)
			System.out.println("Table " + TABLE_NAME + " was successfully created");
		else
			System.out.println("Table " + TABLE_NAME + " already exists");
		
	}
	
	@Override
	public void removeDB() {
		
		super.table = TABLE_NAME;
		super.removeDB();
		
	}
	
	public static boolean createUser(User user) throws SQLException {
		
		DBConnection connect = new DBConnection();
		Connection connection = connect.connect();
		CRUD crud = new CRUD(connection);
		
		String sql = "SELECT * FROM " + DB_NAME + "." + TABLE_NAME
				+ " WHERE " + USERNAME + "='" + user.getUsername() + "'";
		
		boolean exists = false;
		
		try {
		
			ResultSet rs = crud.read(sql);
			exists = rs.next();
		
		} catch (SQLException e) {
			
			System.err.println("Error performing query: " + e.toString());
			
		}
		
		if (!exists) {
			
			String username = user.getUsername() == null ? "NULL" : "'" + user.getUsername() + "'";
			String pass = user.getPassword() == null ? "NULL" : "'" + user.getPassword() + "'";
			String salt = user.getSalt() == null ? "NULL" : "'" + user.getSalt() + "'";
			String type = user.getUserType() == null ? "NULL" : "'" + user.getUserType() + "'";
			String salary = user.getSalary() == null ? "NULL" : "'" + user.getSalary() + "'";
			
			sql = "INSERT INTO " + DB_NAME + "." + TABLE_NAME
					+ " SET " + USERNAME + "=" + username
					+ ", " + PASSWORD + "=" + pass
					+ ", " + SALT + "=" + salt
					+ ", " + TYPE + "=" + type
					+ ", " + SALARY + "=" + salary;
			
			boolean success = crud.create(sql);
			connect.disconnect();
			
			return success;
			
		}
		
		return false;
		
	}
	
	public static ArrayList<User> readUser() throws SQLException {
		
		ArrayList<User> userList = new ArrayList<User>();
		User user = null;
		
		DBConnection connect = new DBConnection();
		Connection connection = connect.connect();
		CRUD crud = new CRUD(connection);
		
		String sql = "SELECT * FROM " + DB_NAME + "." + TABLE_NAME;
		
		ResultSet rs = crud.read(sql);
		
		while (rs.next()) {
			
			user = new User();
			user.setUsername(rs.getString(USERNAME));
			user.setPassword(rs.getString(PASSWORD));
			user.setSalt(String.valueOf(rs.getInt(SALT)));
			user.setUserType(rs.getString(TYPE));
			user.setSalary(String.valueOf(rs.getDouble(SALARY)));
			
			userList.add(user);
			
		}
		
		connect.disconnect();
		
		return userList;
		
	}
	
	public static boolean deleteUser(User user) {
		
		DBConnection connect = new DBConnection();
		Connection connection = connect.connect();
		CRUD crud = new CRUD(connection);
		
		String username = user.getUsername() == null ? "NULL" : "'" + user.getUsername() + "'";
		
		String sql = "DELETE FROM " + DB_NAME + "." + TABLE_NAME
				+ " WHERE " + USERNAME + "=" + username;
		
		boolean success = crud.delete(sql);
		connect.disconnect();
		
		return success;
		
	}

}
