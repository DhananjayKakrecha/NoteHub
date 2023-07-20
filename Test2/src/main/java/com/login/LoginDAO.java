package com.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public LoginDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    // Establishes a database connection
    private void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Could not load database driver");
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
    
 // Closes the database connection
    private void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
 // Validates the login credentials
    public boolean validateCredentials(LoginModel loginModel) throws SQLException {
        boolean isValid = false;
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(query)) {
            statement.setString(1, loginModel.getUsername());
            statement.setString(2, loginModel.getPassword());

            ResultSet resultSet = statement.executeQuery();
            isValid = resultSet.next();
            
        }

        disconnect();

        return isValid;
    }
    
    
}
