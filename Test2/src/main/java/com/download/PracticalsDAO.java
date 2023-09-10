package com.download;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PracticalsDAO {
	private final String databaseUrl = "jdbc:mysql://localhost:3306/t1";
	private final String username = "root";
	private final String password = "root";
	private NotesDAO dao = new NotesDAO();

	private Practicals createPracticalFromRS(ResultSet rs) throws SQLException {
		Practicals prac = new Practicals();
		prac.setPid(rs.getInt("pid"));
		prac.setName(rs.getString("name"));
		prac.setFileName(rs.getString("file_name"));
		prac.setSubName(rs.getString("sub_name"));
		prac.setTopic(rs.getString("topic"));
		prac.setUnit(rs.getString("unit"));
		prac.setWeightage(rs.getString("weightage"));

		return prac;
	}

	public List<Practicals> getPracticals(String search) {
		List<Practicals> pracs = new ArrayList<>();
		String searchParameter = "%" + search + "%";
		String query = "Select * from practicals where topic like ? or sub_name like ? or file_name like ? or name like ?";

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
			preparedStatement.setString(4, searchParameter);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				pracs.add(createPracticalFromRS(resultSet));
			}

			resultSet.close();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return pracs;
	}

	public List<Practicals> getFilteredPracticals(String filter,String uname){
		List<Practicals> pracs = new ArrayList<>();
		String filterParam = "%" +filter+ "%";
		String query = "Select practicals.topic,practicals.name,practicals.unit,practicals.file_name,practicals.sub_name,practicals.pid,practicals.weightage from practicals,"+uname+" where (practicals.file_name = "+uname+".file_name and labels = ?) and (practicals.topic like ? or practicals.sub_name like ? or practicals.unit like ?)";
		
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
				pracs.add(createPracticalFromRS(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pracs;
	}
	
//	public boolean isPinned(String uname, String fileName) {
//		boolean result = false;
//		String query = "Select * from " + uname + " where (file_name = ? and labels = ?)";
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			Connection connection = DriverManager.getConnection(databaseUrl, username, password);
//
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, fileName);
//			preparedStatement.setString(2, "NULL");
//
//			ResultSet resultSet = preparedStatement.executeQuery();
//			result = resultSet.next();
//
//			connection.close();
//			preparedStatement.close();
//			resultSet.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return result;
//	}

//	public int pinPractical(String uname, String fileName) {
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

//	public int unPinPractical(String uname, String fileName) {
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
	
	public List<Practicals> getPinnedPracticals(String uname){
		List<Practicals> pracs = new ArrayList<>();
		
		String query = "Select practicals.file_name,practicals.sub_name,practicals.name,practicals.topic,practicals.unit,practicals.pid,practicals.weightage from practicals," + uname
				+ " where (practicals.file_name = " + uname + ".file_name and labels = ?)";

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
				pracs.add(createPracticalFromRS(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pracs;
	}
	
//	public int pinToLabelPractical(String uname,String fileName,String label) {
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
	
//	public int unPinToLabelPractical(String uname,String label, String fileName) {
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
	
	public List<Practicals> getLabelledPracticals(String uname,String label){
		List<Practicals> pracs = new ArrayList<>();
		String query = "Select practicals.file_name,practicals.sub_name,practicals.name,practicals.topic,practicals.unit,practicals.pid,practicals.weightage from practicals," + uname
				+ " where (practicals.file_name = " + uname + ".file_name and labels = ?)";
		
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
				pracs.add(createPracticalFromRS(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pracs;
	}
	
	public List<Practicals> getFilteredLabelPracticals(String filter,String uname, String label){
		List<Practicals> pracs = new ArrayList<>();
		String filterParam = "%" +filter+ "%";
		String query = "Select practicals.topic,practicals.name,practicals.unit,practicals.file_name,practicals.sub_name,practicals.pid,practicals.weightage from practicals,"+uname+" where (practicals.file_name = "+uname+".file_name and labels = ?) and (practicals.topic like ? or practicals.sub_name like ? or practicals.unit like ?)";
		
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
				pracs.add(createPracticalFromRS(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pracs;
	}
	
	public List<Practicals> getNotesFromSubject(String subname,String uname){
		List<Practicals> pracs = new ArrayList<>();
		String query = "Select * from practicals where sub_name = ? and name = ?";
		
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
				pracs.add(createPracticalFromRS(resultSet));
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pracs;
	}
}
