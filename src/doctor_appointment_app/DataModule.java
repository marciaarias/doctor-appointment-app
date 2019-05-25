package doctor_appointment_app;

import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataModule {
	
	Properties properties;
	FileInputStream reader;
	int id;

	//Create connection between the application and database.
	
	public Connection getConnection() throws Exception {
		
		try{			
			reader = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
			
			properties = new Properties();
			properties.load(reader);
		  
			Connection connection = DriverManager.getConnection(
						  				properties.getProperty("url"),
						  				properties.getProperty("username"),
						  				properties.getProperty("password")
									);

			return connection;
		  
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	  
		return null;
	  
	}
	
	//Return content of a column in a table in the database.
	
	public String getColumnAsString(Connection connection, String query, String columnName) {
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				return resultSet.getString(columnName);
				
			} else {
				return null;
				
			}

			
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		
		return null;

	}
	
	//Modify data in the database.
	
	public void updateData(Connection connection, String query) {
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate(query);
			
		} catch(Exception exception) {
			exception.printStackTrace();
		}

	}
	
	//Select data in the database and populate a JTable.
	
	public void selectData(Connection connection, String query, JTable table) {
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	//Fill JComboBox with data from a database.
	
	public void fillComboBox(Connection connection, String query, JComboBox<String> comboBox, String columnName) {
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				comboBox.addItem(resultSet.getString(columnName));  

			}
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		
	}

}
