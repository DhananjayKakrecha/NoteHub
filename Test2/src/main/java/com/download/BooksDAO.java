package com.download;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO {
	private final String databaseUrl = "jdbc:mysql://localhost:3306/t1";
	private final String username = "root";
	private final String password = "root";
	private NotesDAO dao = new NotesDAO();

	private Books createBooksFromRS(ResultSet rs) throws SQLException{
		Books book = new Books();
		book.setName(rs.getString("name"));
		book.setFileName(rs.getString("file_name"));
		book.setSubName(rs.getString("sub_name"));
		
		return book;
	}
	
	public List<Books> getBooks(String search) {
		List<Books> books = new ArrayList<>();
		String searchParameter = "%" + search + "%";
		String query = "Select * from books where sub_name like ? or file_name like ? or name like ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}

		try {
			Connection con = DriverManager.getConnection(databaseUrl, username, password);
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, searchParameter);
			preparedStatement.setString(2, searchParameter);
			preparedStatement.setString(3, searchParameter);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				books.add(createBooksFromRS(resultSet));
			}

			resultSet.close();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return books;
	}
	
	public List<Books> getFilteredbooks(String filter,String uname){
		List<Books> books = new ArrayList<>();
		String filterParam = "%" +filter+ "%";
		String query = "Select books.name,books.file_name,books.sub_name from books,"+uname+" where (books.file_name = "+uname+".file_name and labels = ?) and (books.name like ? or books.sub_name like ? or books.file_name like ?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "NULL");
			preparedStatement.setString(2, filterParam);
			preparedStatement.setString(3, filterParam);
			preparedStatement.setString(4, filterParam);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				books.add(createBooksFromRS(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return books;
	}
	
//	public int pinBook(String uname, String fileName) {
//		int result = 0;
//		String query = "Insert into " + uname + "(file_name,labels) values (?,?)";
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		try {
//			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
//
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, fileName);
//			preparedStatement.setString(2, "NULL");
//
//			result = preparedStatement.executeUpdate();
//
//			connection.close();
//			preparedStatement.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return result;
//	}
//
//	public int unPinBook(String uname, String fileName) {
//		int result = 0;
//		String query = "Delete from " + uname + " where (file_name = ? and labels = ?)";
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		try {
//			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
//
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, fileName);
//			preparedStatement.setString(2, "NULL");
//
//			result = preparedStatement.executeUpdate();
//
//			connection.close();
//			preparedStatement.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	public List<Books> getPinnedbooks(String uname){
		List<Books> books = new ArrayList<>();
		
		String query = "Select books.file_name,books.sub_name,books.name from books," + uname
				+ " where (books.file_name = " + uname + ".file_name and labels = ?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "NULL");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				books.add(createBooksFromRS(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return books;
	}
	
//	public int pinToLabelBook(String uname,String fileName,String label) {
//		int result = 0;
//		String query = "Insert into " +uname+ "(file_name,labels) values (?,?)";
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
//
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, fileName);
//			preparedStatement.setString(2, label);
//
//			result = preparedStatement.executeUpdate();
//			
//			connection.close();
//			preparedStatement.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return result;
//	}
//	
//	public int unPinToLabelBook(String uname,String label, String fileName) {
//		int result = 0;
//		String query = "Delete from " +uname+ " where (file_name = ? and labels = ?)";
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
//
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, fileName);
//			preparedStatement.setString(2, label);
//
//			result = preparedStatement.executeUpdate();
//			
//			connection.close();
//			preparedStatement.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
	
	public List<Books> getLabelledbooks(String uname,String label){
		List<Books> books = new ArrayList<>();
		String query = "Select books.file_name,books.sub_name,books.name from books," + uname
				+ " where (books.file_name = " + uname + ".file_name and labels = ?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, label);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				books.add(createBooksFromRS(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return books;
	}
	
	public List<Books> getFilteredLabelbooks(String filter,String uname, String label){
		List<Books> books = new ArrayList<>();
		String filterParam = "%" +filter+ "%";
		String query = "Select books.name,books.file_name,books.sub_name from books,"+uname+" where (books.file_name = "+uname+".file_name and labels = ?) and (books.name like ? or books.sub_name like ? or books.file_name like ?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, label);
			preparedStatement.setString(2, filterParam);
			preparedStatement.setString(3, filterParam);
			preparedStatement.setString(4, filterParam);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				books.add(createBooksFromRS(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return books;
	}
	
	public List<Books> getNotesFromSubject(String subname,String uname){
		List<Books> books = new ArrayList<>();
		String query = "Select * from books where sub_name = ? and name = ?";
		
		String name = dao.getNameFromUsername(uname);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, subname);
			preparedStatement.setString(2, name);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				books.add(createBooksFromRS(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return books;
	}
}
