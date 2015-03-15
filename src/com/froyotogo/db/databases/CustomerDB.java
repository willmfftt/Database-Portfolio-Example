package com.froyotogo.db.databases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.froyotogo.Customer;
import com.froyotogo.db.CRUD;
import com.froyotogo.db.DBConnection;

/**
 * Handles all operations on the customer table, defines the CRUD operations
 * 
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */

public class CustomerDB extends Database {

	public static final String TABLE_NAME = "customerInfo900650079";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String ADDRESS = "address";
	public static final String PHONE_NUM = "phoneNum";
	public static final String EMAIL = "email";
	public static final String BIRTHDAY = "birthday";

	public CustomerDB(DBConnection connect) {

		super(connect);

	}

	@Override
	public void createDB() {

		String sql = "CREATE TABLE IF NOT EXISTS " + DB_NAME + "." + TABLE_NAME
				+ " (" + FIRST_NAME + " VARCHAR(255) NOT NULL, " + LAST_NAME
				+ " VARCHAR(255) NOT NULL, " + ADDRESS + " VARCHAR(255), "
				+ PHONE_NUM + " VARCHAR(30), " + EMAIL
				+ " VARCHAR(255) NOT NULL, " + BIRTHDAY + " DATE)";

		Connection connection = connect.connect();
		CRUD crud = new CRUD(connection);

		boolean success = crud.create(sql);

		connect.disconnect();

		if (success)
			System.out.println("Table " + TABLE_NAME
					+ " was successfully created");
		else
			System.out.println("Table " + TABLE_NAME + " already exists");

	}

	@Override
	public void removeDB() {

		super.table = TABLE_NAME;
		super.removeDB();

	}

	public static boolean createCustomer(Customer customer) {

		DBConnection connect = new DBConnection();
		Connection connection = connect.connect();
		CRUD crud = new CRUD(connection);

		String sql = "SELECT * FROM " + CustomerDB.DB_NAME + "."
				+ CustomerDB.TABLE_NAME + " WHERE " + CustomerDB.FIRST_NAME
				+ "='" + customer.getFirstName() + "' AND "
				+ CustomerDB.LAST_NAME + "='" + customer.getLastName() + "'";

		ResultSet rs = crud.read(sql);

		boolean hasRow = true;

		try {

			hasRow = rs.next();

		} catch (SQLException e1) {

			hasRow = false;

		}

		if (!hasRow) {

			sql = "INSERT INTO " + CustomerDB.DB_NAME + "."
					+ CustomerDB.TABLE_NAME + " SET " + CustomerDB.FIRST_NAME
					+ "='" + customer.getFirstName() + "', "
					+ CustomerDB.LAST_NAME + "='" + customer.getLastName()
					+ "', " + CustomerDB.ADDRESS + "='" + customer.getAddress()
					+ "', " + CustomerDB.PHONE_NUM + "='"
					+ customer.getPhoneNum() + "', " + CustomerDB.EMAIL + "='"
					+ customer.getEmail() + "', " + CustomerDB.BIRTHDAY + "='"
					+ customer.getBirthday() + "'";

			boolean success = crud.create(sql);
			connect.disconnect();

			return success;

		}

		return false;

	}

	public static ArrayList<Customer> readCustomer() throws SQLException {

		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Customer customer = null;

		DBConnection connect = new DBConnection();
		Connection connection = connect.connect();
		CRUD crud = new CRUD(connection);

		String sql = "SELECT * FROM " + DB_NAME + "." + TABLE_NAME;

		ResultSet rs = crud.read(sql);

		while (rs.next()) {

			customer = new Customer(rs.getString(CustomerDB.FIRST_NAME),
					rs.getString(CustomerDB.LAST_NAME),
					rs.getString(CustomerDB.ADDRESS),
					rs.getString(CustomerDB.PHONE_NUM),
					rs.getString(CustomerDB.EMAIL), rs.getDate(
							CustomerDB.BIRTHDAY).toString());

			customerList.add(customer);

		}

		connect.disconnect();

		return customerList;

	}

	public static boolean deleteCustomer(Customer customer) {

		DBConnection connect = new DBConnection();
		Connection connection = connect.connect();
		CRUD crud = new CRUD(connection);

		String sql = "DELETE FROM " + DB_NAME + "." + TABLE_NAME + " WHERE "
				+ FIRST_NAME + "='" + customer.getFirstName() + "' AND "
				+ LAST_NAME + "='" + customer.getLastName() + "'";

		boolean deleted = crud.delete(sql);
		connect.disconnect();

		return deleted;

	}

}
