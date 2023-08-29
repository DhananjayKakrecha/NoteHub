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
		String title = request.getParameter("title");
		String description = request.getParameter("desc");
		String action = request.getParameter("action");

		if (action.equals("add")) {
			int result = dao.addNote(uname, title, description);
		} else if (action.equals("update")) {
			int nid = Integer.parseInt(request.getParameter("nid"));
			int rs1 = dao.updateVC(nid, uname,title,description);
//			System.out.println(nid + action);
			int result = dao.updateNote(nid, title, description);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("NotesDetails.jsp");
		dispatcher.forward(request, response);
	}

}
