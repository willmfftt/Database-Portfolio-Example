package com.froyotogo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Facilitates the Create, Read, Update, Delete operations on the database
 * 
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */

public class CRUD {
	
	private final Connection connection;
	private PreparedStatement preparedStmt;
	
	public CRUD(Connection connection) {
		
		this.connection = connection;
		
	}
	
	public boolean create(String sql) {
		
		int rows = 0;
		
		try {
			
			preparedStmt = connection.prepareStatement(sql);
			rows = preparedStmt.executeUpdate();
			
		} catch (SQLException e) {
			
			displayError(e);
			
		}
		
		return rows == 1;
		
	}
	
	public ResultSet read(String sql) {
		
		ResultSet rs = null;
		
		try {
			
			preparedStmt = connection.prepareStatement(sql);
			rs = preparedStmt.executeQuery();
			
		} catch (SQLException e) {
			
			displayError(e);
			
		}
		
		return rs;
		
	}
	
	public boolean update(String sql) {
		
		return create(sql);	
		
	}
	
	public boolean delete(String sql) {
		
		return create(sql);
		
	}
	
	void displayError(Exception e) {	
		
		System.err.println("Error preparing statement: " + e.toString());	
		
	}

}
