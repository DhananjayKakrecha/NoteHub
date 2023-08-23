package com.note;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoteDAO dao;
	
	@Override
	public void init() throws ServletException {
		dao = new NoteDAO();
		super.init();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		String title = request.getParameter("title");
		String description = request.getParameter("desc");
		
		int result = dao.addNote(uname, title, description);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("NotesDetails.jsp");
		dispatcher.forward(request, response);
	}

}
