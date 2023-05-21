package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	public static Connection startConnection() {
		 final String url ="jdbc:mysql://localhost:3306/university";
		 final String user ="root";
		 final String password ="";
		 
		 try {
			return DriverManager.getConnection(url, user, password);
				
			
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}
	
	public static void closeConnection() {
		try {
			startConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
