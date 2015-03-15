package com.froyotogo.gui.manager;

import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.froyotogo.Customer;
import com.froyotogo.db.databases.CustomerDB;

/**
 * Screen for the manager to add customers to the database
 * 
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */

public class CustomerAddScreen {

	public static boolean showGUI() {

		String[] options = { "Add", "Cancel" };

		JPanel firstNamePane = new JPanel();
		JPanel lastNamePane = new JPanel();
		JPanel addressPane = new JPanel();
		JPanel phoneNumPane = new JPanel();
		JPanel emailPane = new JPanel();
		JPanel birthdayPane = new JPanel();
		JPanel panel = new JPanel();

		JLabel firstNameLabel = new JLabel("First Name:");
		JTextField firstNameField = new JTextField(10);
		firstNamePane.add(firstNameLabel);
		firstNamePane.add(firstNameField);

		JLabel lastNameLabel = new JLabel("Last Name:");
		JTextField lastNameField = new JTextField(10);
		lastNamePane.add(lastNameLabel);
		lastNamePane.add(lastNameField);

		JLabel addressLabel = new JLabel("Address:   ");
		JTextField addressField = new JTextField(10);
		addressPane.add(addressLabel);
		addressPane.add(addressField);

		JLabel phoneNumLabel = new JLabel("Phone #: ");
		JTextField phoneNumField = new JTextField(10);
		phoneNumPane.add(phoneNumLabel);
		phoneNumPane.add(phoneNumField);

		JLabel emailLabel = new JLabel("Email:   ");
		JTextField emailField = new JTextField(10);
		emailPane.add(emailLabel);
		emailPane.add(emailField);

		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");

		JLabel birthdayLabel = new JLabel("Birthday:");
		JFormattedTextField birthdayField = new JFormattedTextField(dateFmt);
		birthdayField.setColumns(10);
		birthdayPane.add(birthdayLabel);
		birthdayPane.add(birthdayField);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(firstNamePane);
		panel.add(lastNamePane);
		panel.add(addressPane);
		panel.add(phoneNumPane);
		panel.add(emailPane);
		panel.add(birthdayPane);

		int option = JOptionPane.showOptionDialog(null, panel, "Add Customer",
				JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				options, options[0]);
		
		if (option == 0) {
			
			Customer customer = new Customer();
			
			customer.setFirstName(firstNameField.getText());
			customer.setLastName(lastNameField.getText());
			customer.setAddress(addressField.getText());
			customer.setPhoneNum(phoneNumField.getText());
			customer.setEmail(emailField.getText());
			customer.setBirthday(birthdayField.getText());
			
			boolean added = CustomerDB.createCustomer(customer);
			
			if (!added)
				JOptionPane.showMessageDialog(null, "Customer couldn't be added");
			
			return added;
			
		}
		
		return false;

	}

}
