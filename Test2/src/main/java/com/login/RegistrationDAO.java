package com.login;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationDAO {
	private final String url = "jdbc:mysql://localhost:3306/t1";
	private final String username = "root";
	private final String password = "root";
	
	public void saveRegistration(Registration registration) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "INSERT INTO users (username, password, fullname, email,type) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, registration.getUsername());
			statement.setString(2, registration.getPassword());
			statement.setString(3, registration.getFullName());
			statement.setString(4, registration.getEmail());
			statement.setString(5, registration.getType());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean generateUser(String uname) {
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			String sql = "Create table " +uname+ " (file_name varchar(255),labels varchar(255))";
			Statement statement = conn.createStatement();
			result = statement.execute(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
