package doctor_appointment_app;
import java.util.Properties;

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
		  
		}catch(Exception e){
				e.printStackTrace();
		}
	  
		return null;
	  
	}
	
	//Return content of a column in a table.
	
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
	
	//Update data in a table.
	
	public void updateData(Connection connection, String query) {
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate(query);
			
		} catch(Exception exception) {
			exception.printStackTrace();
		}

	}

}
