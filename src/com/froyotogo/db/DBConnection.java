package com.froyotogo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles the connection to the database
 * 
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */

public class DBConnection {
	
	private static final String HOST = "csit211.db.7914529.hostedresource.com";
	private static final String USERNAME = "csit211";
	private static final String PASSWORD = "F@llCSIT211";
	
	private Connection connection;
	
	public DBConnection() {}

	public Connection connect() {

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

		} catch (Exception e) {

			System.err.println("Couldn't load JDBC driver");
			e.printStackTrace();

		}

		try {

			connection = DriverManager.getConnection("jdbc:mysql://" + HOST,
					USERNAME, PASSWORD);

		} catch (SQLException e) {

			System.err.println("Could not connect to database");
			e.printStackTrace();

		}
		
		if (connection != null)
			System.out.println("Successfully connected to database");

		return connection;

	}
	
	public void disconnect() {
		
		try {
			
			connection.close();
			
		} catch (SQLException e) {
			
			System.err.println("Error closing database connection: " + e.toString());
			
		}
		
	}

}
