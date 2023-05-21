package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import model.DatabaseConnection;
import model.Student;

public class StudentController {
	
	static Connection connection;
	
	static ArrayList<Student> students = new ArrayList<Student>();
	
	public StudentController() {
		
	}
	
	public static void add(int code, String name, double test1, double test2) throws SQLException {
		 
			connection = DatabaseConnection.startConnection();
			String sql = "INSERT INTO students (code, name,  test1, test2) VALUES (?, ?, ?, ?)";
		
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, code);
			stmt.setString(2, name);
			stmt.setDouble(3, test1);
			stmt.setDouble(4, test2);

			stmt.executeUpdate();

			stmt.close();
			DatabaseConnection.closeConnection();
		
	}
	
	public static void delete(int code) throws SQLException{
		connection = DatabaseConnection.startConnection();
		String sql = "DELETE FROM students WHERE code = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, code);

		stmt.executeUpdate();

		stmt.close();

		DatabaseConnection.closeConnection();
	}
	
	public static void edit(int code, String name, double test1, double test2) {
		try {
	    	connection = DatabaseConnection.startConnection();
	        String sql = "UPDATE students SET name = ?, test1 = ?, test2 = ? WHERE code = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);

	        statement.setString(1, name);
	        statement.setDouble(2, test1);
	        statement.setDouble(3,test2);
	        statement.setInt(4, code);

	        statement.executeUpdate();

	        statement.close();
	        connection.close();
	        DatabaseConnection.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static ArrayList<Student> list() {
		ArrayList<Student> students = new ArrayList<Student>();

		try {
			connection = DatabaseConnection.startConnection();
			String sql = "SELECT * FROM students";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int code = resultSet.getInt("code");
				String name = resultSet.getString("name");
				double test1 = resultSet.getDouble("test1");
				double test2 = resultSet.getDouble("test2");

				Student student = new Student(code, name, test1, test2);
				students.add(student);
			}

			resultSet.close();
			statement.close();
			connection.close();
			DatabaseConnection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

}
