package com.download;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NotesDAO {
	private final String databaseUrl = "jdbc:mysql://localhost:3306/t1";
	private final String username = "root";
	private final String password = "root";

	public List<Notes> getAllNotes(String subName) {
		List<Notes> notes = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
			String query = "Select * from notes where sub_name = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, subName);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Notes note = createNoteFromResultSet(resultSet);
				notes.add(note);
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return notes;
	}

	private Notes createNoteFromResultSet(ResultSet rs) throws SQLException {
		Notes notes = new Notes();

		notes.setFileName(rs.getString("file_name"));
		notes.setName(rs.getString("name"));
		notes.setSubName(rs.getString("sub_name"));
		notes.setTopic(rs.getString("topic"));
		notes.setUnit(rs.getString("unit"));
		return notes;
	}

	public List<Notes> getNotes(String search) {
		List<Notes> notes = new ArrayList<>();
		String searchParameter = "%" + search + "%";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
			String query = "Select * from notes where topic like ? or sub_name like ? or name like ? or file_name like ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, searchParameter);
			preparedStatement.setString(2, searchParameter);
			preparedStatement.setString(3, searchParameter);
			preparedStatement.setString(4, searchParameter);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Notes note = createNoteFromResultSet(resultSet);
				notes.add(note);
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return notes;
	}
	
	public List<Notes> getFilterdNotes(String uname, String filter){
		List<Notes> notes = new ArrayList<>();
		String searchParameter = "%" +filter+ "%";
		
		String query = "Select notes.topic,notes.name,notes.sub_name,notes.file_name,notes.unit from notes,"+ uname + " where (notes.file_name = "+ uname+ ".file_name and labels = ?) and (notes.topic like ? or notes.sub_name like ? or notes.unit like ?)";
		
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
			preparedStatement.setString(2, searchParameter);
			preparedStatement.setString(3, searchParameter);
			preparedStatement.setString(4, searchParameter);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Notes note = createNoteFromResultSet(resultSet);
				notes.add(note);
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notes;
	}

	public List<Notes> getPinnedNotes(String uname) {
		List<Notes> notes = new ArrayList<>();
		String p2 = uname + ".file_name";

		String query = "Select notes.file_name,notes.sub_name,notes.name,notes.topic,notes.unit from notes," + uname
				+ " where notes.file_name = " + uname + ".file_name and labels = 'NULL'";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Notes note = createNoteFromResultSet(resultSet);
				notes.add(note);
			}
			
			connection.close();
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notes;
	}

	public boolean isPinned(String uname, String fileName) {
		boolean result = false;
		String query = "Select * from " + uname + " where file_name = ? and labels = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, fileName);
			preparedStatement.setString(2, "NULL");

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

	public int pinNote(String uname, String fileName) {
		int result = 0;
		String query = "Insert into " + uname + " values (?,?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, fileName);
			preparedStatement.setString(2, "NULL");

			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int unPinNote(String uname, String fileName) {
		int result = 0;
		String query = "Delete from " + uname + " where file_name = ? and labels = 'NULL'";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, fileName);

			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<String> getLabels(String uname){
		List<String> list = new ArrayList<>();
		String query = "Select label from Labels where uname = '" +uname+ "'";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				list.add(resultSet.getString("label"));
			}
			
			connection.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int pinToLabel(String uname,String fileName,String label) {
		int result=0;
		String query = "Insert into " +uname+ " values(?,?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, fileName);
			preparedStatement.setString(2, label);

			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Notes> getAllLabelNotes(String uname,String label){
		List<Notes> notes = new ArrayList<>();
		String query = "Select notes.file_name,notes.sub_name,notes.name,notes.topic,notes.unit from notes," + uname
				+ " where notes.file_name = " + uname + ".file_name and labels = '" +label+ "'";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Notes note = createNoteFromResultSet(resultSet);
				notes.add(note);
			}
			
			connection.close();
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notes;
	}
	
	public int unPinLabelNote(String uname,String label,String fileName) {
		int result = 0;
		String query = "Delete from " +uname+ " where file_name = ? and labels = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, fileName);
			preparedStatement.setString(2, label);

			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean isLabelled(String uname,String fileName,String label) {
		boolean result = false;
		String query = "Select * from " +uname+ " where file_name = ? and labels = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, fileName);
			preparedStatement.setString(2, label);

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
	
	public int createLabel(String uname,String label) {
		int result = 0;
		String query = "Insert into labels values(?,?)";
		
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
			preparedStatement.setString(2, label);

			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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
	
}
