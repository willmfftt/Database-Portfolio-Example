package com.froyotogo.db.databases;

import java.sql.Connection;

import com.froyotogo.db.CRUD;
import com.froyotogo.db.DBConnection;

/**
 * Abstract class to represent the database tables
 * 
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */

public abstract class Database {
	
	public static final String DB_NAME = "csit211";
	
	protected String table;
	protected DBConnection connect;
	
	public Database(DBConnection connect) {
		
		this.connect = connect;
		
	}

	public void createDB() {}
	
	public void removeDB() {
		
		String sql = "DROP TABLE " + table;
		
		Connection connection = connect.connect();
		CRUD crud = new CRUD(connection);
		
		boolean success = crud.delete(sql);
		
		connect.disconnect();
		
		if (success)
			System.out.println("Table " + table + " was successfully deleted");
		else
			System.out.println("Table " + table + " could not be deleted");
		
	}
	
}
