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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("username");
		System.out.println(uname);
		String title = request.getParameter("title");
		String description = request.getParameter("desc");
		String action = request.getParameter("action");
		
		String type = (String)request.getSession().getAttribute("type");

		if (action.equals("add") && type.equals("teacher")) {
			int result = dao.addNote(uname, title, description);
			response.sendRedirect("Teachers.jsp");
		} else if (action.equals("update") && type.equals("teacher")) {
			int nid = Integer.parseInt(request.getParameter("nid"));
			int rs1 = dao.updateVC(nid, uname,title,description);
//			System.out.println(nid + action);
			int result = dao.updateNote(nid, title, description);
			response.sendRedirect("Teachers.jsp");
		} else if(action.equals("add") && type.equals("student")) {
			int result = dao.addNote(uname, title, description);
			response.sendRedirect("NotesDetails.jsp");
		} else if(action.equals("update") && type.equals("student")) {
			int nid = Integer.parseInt(request.getParameter("nid"));
			int rs1 = dao.updateVC(nid, uname,title,description);
//			System.out.println(nid + action);
			int result = dao.updateNote(nid, title, description);
			response.sendRedirect("NotesDetails.jsp");
		}

//		RequestDispatcher dispatcher = request.getRequestDispatcher("NotesDetails.jsp");
//		dispatcher.forward(request, response);
	}

}
