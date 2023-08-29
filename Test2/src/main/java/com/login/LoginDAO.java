package com.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	private String jdbcURL="jdbc:mysql://localhost:3306/t1";
    private String jdbcUsername="root";
    private String jdbcPassword="root";

    
    // Validates the login credentials
    public boolean validateCredentials(String usrname,String password){
        boolean isValid = false;
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
        	Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e) {
        	System.out.println(e);
        }
        
        try {
        	Connection con = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        	PreparedStatement preparedStatement = con.prepareStatement(query);
        	preparedStatement.setString(1, usrname);
        	preparedStatement.setString(2, password);
        	
        	ResultSet rs = preparedStatement.executeQuery();
        	isValid = rs.next();
     
        	rs.close();
        	preparedStatement.close();
        	con.close();
        }catch(SQLException e) {
        	System.out.println(e);
        }
        
        return isValid;
    }
    
    
}
