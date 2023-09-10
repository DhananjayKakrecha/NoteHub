package com.note;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
		String query = "Select title,description,username,Date_Format(date, '%d-%m-%Y %h:%i %p') AS date,nid from note where username = ? order by date desc";
		
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
		Note note = getNoteById(nid);
		String query = "Insert into sharedNotes(sender,receiver,date,title,description) values(?,?,now(),?,?)";
		
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
			ps.setString(3,note.getTitle());
			ps.setString(4, note.getDescription());
			
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
		String query = "Select sid,title,sender,Date_Format(sharedNotes.date, '%d-%m-%Y %h:%i %p') As date from sharedNotes where sharedNotes.receiver = ? order by date desc";
		
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
	
	public Note getSharedNote(int sid) {
		Note note = new Note();
		String query = "Select title,description from sharedNotes where sid = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, sid);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			
			note.setDescription(rs.getString("description"));
			note.setTitle(rs.getString("title"));
			
			connection.close();
			preparedStatement.close();
			rs.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return note;
	}
	
	private Note createSharedNoteFromRS(ResultSet rs) throws SQLException{
		Note note = new Note();
		note.setId(rs.getInt("sid"));
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
	
	public Note getNoteById(int nid) {
		Note note = new Note();
		String query = "Select title,description,Date_Format(date,'%d-%m-%Y %h-%i %p') as date from note where nid = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,nid);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				note.setTitle(rs.getString("title"));
				note.setDescription(rs.getString("description"));
				note.setDate(rs.getString("date"));
			}
			
			connection.close();
			preparedStatement.close();
			rs.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return note;
	}
	
	public int updateNote(int nid,String title,String description) {
		int result = 0;
		String query = "Update note set title = ?,description = ?,date = now() where nid = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,title);
			preparedStatement.setString(2, description);
			preparedStatement.setInt(3,nid);
			
			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
	
	public int deleteNote(int nid) {
		int result = 0;
		String query = "Delete from note where nid = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,nid);
			
			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		
		return result;
	}
	
	public int pinNote(int nid, String uname, String title) {
		int result = 0;
		String query = "Insert into "+ uname+ " values(?,?,?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,nid);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, "NULL");
			
			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
	
	public int unPinNote(int nid,String uname) {
		int result = 0;
		String query = "Delete from " +uname+ " where nid = ? and labels = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, nid);
			preparedStatement.setString(2, "NULL");
			
			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
	
	public boolean isPinned(String uname,int nid) {
		boolean result = false;
		String query = "Select * from "+uname+" where nid = ? and labels = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, nid);
			preparedStatement.setString(2, "NULL");
			
			ResultSet rs = preparedStatement.executeQuery();
			result = rs.next();
			
			
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
	
	public List<Note> getPinnedNotes(String uname){
		List<Note> notes = new ArrayList<>();
		String query = "Select note.title,note.description,Date_Format(date,'%d-%m-%Y %h:%i %p') as date,note.nid from note," +uname+ " where note.nid = " +uname+ ".nid and " +uname+ ".labels = ? order by note.date desc";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "NULL");
			
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
	
	public boolean isLabelled(String uname,int nid,String label) {
		boolean result = false;
		String query = "Select * from " +uname+ " where nid = ? and labels = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, nid);
			preparedStatement.setString(2, label);
			
			ResultSet rs = preparedStatement.executeQuery();
			result = rs.next();
			
			
			connection.close();
			preparedStatement.close();
			rs.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
	
	public int pinToLabel(int nid,String uname,String label,String title) {
		int result = 0;
		String query = "Insert into " +uname+ " values(?,?,?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,nid);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, label);
			
			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
	
	public int unPinLabelNote(int nid,String uname,String label) {
		int result = 0;
		String query = "Delete from " +uname+ " where nid = ? and labels = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,nid);
			preparedStatement.setString(2, label);
			
			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
	
	public List<Note> getAllLabelNotes(String uname,String label){
		List<Note> notes = new ArrayList<>();
		String query = "Select note.title,note.description,Date_Format(date,'%d-%m-%Y %h:%i %p') as date,note.nid from note," +uname+ " where note.nid = " +uname+ ".nid and " +uname+ ".labels = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, label);
			
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
	
	public List<Note> getFilteredLabelNote(String filter, String uname, String label){
		List<Note> notes = new ArrayList<>();
		String query = "Select note.title,note.description,note.username,Date_Format(note.date, '%d-%m-%Y %h:%i %p') AS date,note.nid from note,"+uname+ " where (note.nid = "+uname+".nid and labels = ?) and (note.title like ? and note.username = ?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, label);
			preparedStatement.setString(2, "%" +filter+ "%");
			preparedStatement.setString(3, uname);
			
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
	
	public Note getNoteByIdVC(int nid) {
		Note note = new Note();
		String query = "Select title,description,date as date from note where nid = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,nid);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				note.setTitle(rs.getString("title"));
				note.setDescription(rs.getString("description"));
				note.setDate(rs.getTimestamp("date").toString());
			}
			
			connection.close();
			preparedStatement.close();
			rs.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return note;
	}
	
	public int getVid(int nid,String uname) {
		int vid = 0;
		boolean result = false;
		String query = "Select max(vid) as vid from "+uname+"VersionControl where nid = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, nid);
			
			ResultSet rs = preparedStatement.executeQuery();
			result = rs.next();
			System.out.println(result);
			int isNull = rs.getInt("vid");
			System.out.println("Value of vid from db : " +isNull);
			if(result && isNull != 0) {
				vid = rs.getInt("vid");
			}
			
			connection.close();
			preparedStatement.close();
			rs.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return vid;
	}
	
	public int maxVid(String uname) {
		int maxVid = 0;
		String query = "Select max(vid) as vid from "+uname+"VersionControl";
		
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet rs = preparedStatement.executeQuery();
			result = rs.next();
			int isNull = rs.getInt("vid");
			System.out.println("Value of vid from db : " +isNull);
			if(result && isNull != 0) {
				maxVid = rs.getInt("vid");
			}
			
			connection.close();
			preparedStatement.close();
			rs.close();
		}catch(SQLException e) {
			System.out.println(e);
		}

		
		return maxVid;
	}
	
	public int addNoteVC(int nid,String uname) {
		int result = 0;
		int vid = 0;
		
		Note note = getNoteByIdVC(nid);
		Timestamp timestamp = Timestamp.valueOf(note.getDate());
		String query = "Insert into "+uname+"VersionControl(nid,title,description,branchfrom,dateTime) values(?,?,?,?,?) ";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,nid);
			preparedStatement.setString(2, note.getTitle());
			preparedStatement.setString(3, note.getDescription());
			preparedStatement.setInt(4,vid);
			preparedStatement.setTimestamp(5, timestamp);
			
			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
	
	public int updateVC(int nid,String uname,String title,String description) {
		int result = 0;
		int vid = 0;
		String query = "Insert into " +uname+ "VersionControl(nid,title,description,branchfrom,dateTime) values(?,?,?,?,now()) ";
		
		vid = getVid(nid, uname);
		System.out.println(vid);
		if(vid == 0) {
			addNoteVC(nid, uname);
			vid = getVid(nid, uname);
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,nid);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, description);
			preparedStatement.setInt(4,vid);
			
			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		
		return result;
	}
	
	public List<VersionControl> getVids(int nid,String uname){
		List<VersionControl> vids = new ArrayList<>();
		String query = "Select vid,title,Date_Format(dateTime,'%d-%m-%Y %h:%i %p') as date from "+uname+"VersionControl where nid = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, nid);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				VersionControl vc = new VersionControl();
				vc.setVid(rs.getInt("vid"));
				vc.setDate(rs.getString("date"));
				vc.setTitle(rs.getString("title"));
				vids.add(vc);
			}
			
			connection.close();
			preparedStatement.close();
			rs.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return vids;
	}
	
	public VersionControl getNoteVC(int nid,int vid, String uname) {
		VersionControl vcnote = new VersionControl();
		String query = "Select nid,title,description,branchfrom,Date_Format(dateTime,'%d-%m-%Y %h:%i %p') as date from "+uname+"VersionControl where nid = ? and vid = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, nid);
			preparedStatement.setInt(2, vid);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				vcnote.setNid(rs.getInt("nid"));
				vcnote.setTitle(rs.getString("title"));
				vcnote.setDescription(rs.getString("description"));
				vcnote.setDate(rs.getString("date"));
				vcnote.setBranchFrom(rs.getInt("branchfrom"));
			}
			
			connection.close();
			preparedStatement.close();
			rs.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return vcnote;
	}
	
	public VersionControl getVersionNote(String uname, int branchFrom,int nid) {
		VersionControl vcnote = new VersionControl();
		String query = "Select title,description from "+uname+"VersionControl where vid = ? and nid = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,branchFrom);
			preparedStatement.setInt(2, nid);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				vcnote.setTitle(rs.getString("title"));
				vcnote.setDescription(rs.getString("description"));
			}
			
			connection.close();
			preparedStatement.close();
			rs.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return vcnote;
	}
	
	public int deleteVCNote(int vid, String uname) {
		int result = 0;
		String query = "Delete from "+uname+"VersionControl where vid = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, vid);
			
			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
	
	public int RollBackVC(int vid,int branchFrom,int nid, String uname) {
		int result = 0;
		VersionControl vcnote = getVersionNote(uname, branchFrom, nid);
		int updateNote = updateNote(nid, vcnote.getTitle(),vcnote.getDescription() );
		int delNote = deleteVCNote(vid, uname);
		
		return result;
	}
	
	public boolean getNextVid(int vid,int nid, String uname) {
		boolean result = false;
		String query = "Select vid from "+uname+"VersionControl where nid = ? and vid = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, nid);
			preparedStatement.setInt(2, vid+1);
			
			ResultSet rs = preparedStatement.executeQuery();
			result = rs.next();
			
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	
	}
	
	public int deleteVCNotes(int nid,String uname) {
		int result = 0;
		String query = "Delete from "+uname+"VersionControl where nid = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, nid);
			
			result = preparedStatement.executeUpdate();
			
			connection.close();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
	
}
