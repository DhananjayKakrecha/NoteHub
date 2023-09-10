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

		notes.setNid(rs.getInt("notesId"));
		notes.setFileName(rs.getString("file_name"));
		notes.setName(rs.getString("name"));
		notes.setSubName(rs.getString("sub_name"));
		notes.setTopic(rs.getString("topic"));
		notes.setUnit(rs.getString("unit"));
		notes.setWeightage(rs.getString("weightage"));
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
				notes.add(createNoteFromResultSet(resultSet));
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
		
		String query = "Select notes.topic,notes.name,notes.sub_name,notes.file_name,notes.unit,notes.notesId,notes.weightage from notes,"+ uname + " where (notes.file_name = "+ uname+ ".file_name and labels = ?) and (notes.topic like ? or notes.sub_name like ? or notes.unit like ?)";
		
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

		String query = "Select notes.file_name,notes.sub_name,notes.name,notes.topic,notes.unit,notes.notesId,notes.weightage from notes," + uname
				+ " where (notes.file_name = " + uname + ".file_name and labels = ?)";

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
				notes.add(createNoteFromResultSet(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notes;
	}

	public boolean isPinned(String uname, String fileName) {
		boolean result = false;
		String query = "Select * from " + uname + " where (file_name = ? and labels = ?)";

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
		String query = "Insert into " + uname + "(file_name,labels) values (?,?)";

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
		String query = "Delete from " + uname + " where (file_name = ? and labels = ?)";

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
	
	public List<Notes> getFilterdLabelNotes(String uname, String filter, String label){
		List<Notes> notes = new ArrayList<>();
		String searchParameter = "%" +filter+ "%";
		
		String query = "Select notes.topic,notes.name,notes.sub_name,notes.file_name,notes.unit,notes.notesId,notes.weightage from notes,"+ uname + " where (notes.file_name = "+ uname+ ".file_name and labels = ?) and (notes.topic like ? or notes.sub_name like ? or notes.unit like ?)";
		
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
		String query = "Insert into " +uname+ "(file_name,labels) values(?,?)";
		
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
	
	public int unPinLabelNote(String uname,String label,String fileName) {
		int result = 0;
		String query = "Delete from " +uname+ " where (file_name = ? and labels = ?)";
		
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
		String query = "Select notes.file_name,notes.sub_name,notes.name,notes.topic,notes.unit,notes.notesId,notes.weightage from notes," +uname+ " where (notes.file_name = "+uname+ ".file_name and labels = ?)";
		
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
				notes.add(createNoteFromResultSet(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notes;
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
	
	public boolean checkLabel(String uname, String label) {
		boolean result = false;
		String query = "Select label from labels where uname = ? and label = ?";
		
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
			
			ResultSet rs =preparedStatement.executeQuery();
			result = rs.next();
			
			rs.close();
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
	
	public int deleteLabelNotes(String uname,String label) {
		int result = 0;
		String query = "delete from "+uname+" where labels = ?";

		
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
	
			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteLabel(String uname,String label) {
		int result = 0;
		String query = "Delete from labels where uname = ? and label = ?";
		
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
	
	public String getNameFromUsername(String uname) {
		String name = "";
		String query = "Select fullname from users where username = ?";
		
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
		
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			name = rs.getString("fullname");
			
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}
	
	public int uploadNote(String topic, String uname, String unit, String weightage, String notetype, String fileName, String subject) {
		int result = 0;
		String query = "Insert into "+notetype+"(topic,sub_name,file_name,unit,name,weightage) values(?,?,?,?,?,?)";
		
		String name = getNameFromUsername(uname);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
	
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, topic);
			preparedStatement.setString(2, subject);
			preparedStatement.setString(3, fileName);
			preparedStatement.setString(4, unit);
			preparedStatement.setString(5, name);
			preparedStatement.setString(6, weightage);
	
			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private List<String> getNotesSubjects(String uname){
		List<String> subs = new ArrayList<>();
		String query = "Select sub_name from notes where name = ?";
		
		String name = getNameFromUsername(uname);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println(resultSet.getString("sub_name"));
				if(!subs.contains(resultSet.getString("sub_name"))) {
					subs.add(resultSet.getString("sub_name"));
				}
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return subs;
	}
	
	private List<String> getPracSubjects(String uname){
		List<String> subs = new ArrayList<>();
		String query = "Select sub_name from practicals where name = ?";
		
		String name = getNameFromUsername(uname);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if(!subs.contains(resultSet.getString("sub_name"))) {
					subs.add(resultSet.getString("sub_name"));
				}
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return subs;
	}
	
	private List<String> getBooksSubjects(String uname){
		List<String> subs = new ArrayList<>();
		String query = "Select sub_name from books where name = ?";
		
		String name = getNameFromUsername(uname);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if(!subs.contains(resultSet.getString("sub_name"))) {
					subs.add(resultSet.getString("sub_name"));
				}
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return subs;
	}
	
	public List<String> getSubjects(String uname){
		List<String> subs = new ArrayList<>();
		
		List<String> nsubs = getNotesSubjects(uname);
		List<String> psubs = getPracSubjects(uname);
		List<String> bsubs = getBooksSubjects(uname);
		
		for(String nsub : nsubs) {
			System.out.println(nsub);
			if(!subs.contains(nsub)) {
				subs.add(nsub);
			}
		}
		
		for(String psub : psubs) {
			if(!subs.contains(psub)) {
				subs.add(psub);
			}
		}
		
		for(String bsub : bsubs) {
			if(!subs.contains(bsub)) {
				subs.add(bsub);
			}
		}
		
		return subs;
	}
	
	public List<Notes> getNotesFromSubject(String subname,String uname){
		List<Notes> notes = new ArrayList<>();
		String query = "Select * from notes where sub_name = ? and name = ?";
		
		String name = getNameFromUsername(uname);
		
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
				notes.add(createNoteFromResultSet(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notes;
	}
}
