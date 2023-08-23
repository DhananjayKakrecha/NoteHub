package com.note;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class NoteDAO {
	private final String databaseUrl = "jdbc:mysql://localhost:3306/t1";
	private final String username = "root";
	private final String password = "root";

	public int addNote(String uname,String title,String description) {
		int result = 0;
		String query = "Insert into note(title,description,username,date) values(?,?,?,now())";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, description);
			preparedStatement.setString(3, uname);
			
			result = preparedStatement.executeUpdate();
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
	
	public List<Note> getNote(String uname){
		List<Note> notes = new ArrayList<>();
		String query = "Select title,description,username,Date_Format(date, '%d-%m-%Y %h:%i %p') AS date,nid from note where username = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, uname);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				notes.add(createNoteFromRS(rs));
			}
			
			connection.close();
			preparedStatement.close();
			rs.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return notes;
	}
	
	private Note createNoteFromRS(ResultSet rs) throws SQLException{
		Note note = new Note();
		note.setUsername(rs.getString("username"));
		note.setTitle(rs.getString("title"));
		note.setDescription(rs.getString("description"));
		note.setDate(rs.getString("date"));
		note.setId(rs.getInt("nid"));
		return note;
	}
	
	public boolean checkUsername(String uname) {
		boolean result= false;
		String query = "Select username from users where username = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, uname);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			result = resultSet.next();
			 
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int shareNote(String sender, String receiver,int nid) {
		int result = 0;
		String query = "Insert into sharedNotes values(?,?,now(),?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection con = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, sender);
			ps.setString(2, receiver);
			ps.setInt(3, nid);
			
			result = ps.executeUpdate();
			
			con.close();
			ps.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
	
	public List<Note> getSharedNote(String uname){
		List<Note> notes = new ArrayList<>();
		String query = "Select note.title,sharedNotes.sender,Date_Format(sharedNotes.date, '%d-%m-%Y %h:%i %p') As date from note,sharedNotes where note.nid = sharedNotes.nid and sharedNotes.receiver = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, uname);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				notes.add(createSharedNoteFromRS(rs));
			}
			
			connection.close();
			preparedStatement.close();
			rs.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return notes;
	}
	
	private Note createSharedNoteFromRS(ResultSet rs) throws SQLException{
		Note note = new Note();
		note.setDate(rs.getString("date"));
		note.setSender(rs.getString("sender"));
		note.setTitle(rs.getString("title"));
		return note;
	}
	
	public List<Note> getFilteredNote(String filter, String uname){
		List<Note> notes = new ArrayList<>();
		String query = "Select title,description,username,Date_Format(date, '%d-%m-%Y %h:%i %p') AS date,nid from note where title like ? and username = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" +filter+ "%");
			preparedStatement.setString(2, uname);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				notes.add(createNoteFromRS(rs));
			}
			
			connection.close();
			preparedStatement.close();
			rs.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return notes;
	}
	
}
