package com.note;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PinNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoteDAO dao;
	
	@Override
	public void init() throws ServletException {
		dao = new NoteDAO();
		super.init();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String uname = request.getParameter("username");
		int nid = Integer.parseInt(request.getParameter("nid"));
		String action = request.getParameter("action");
		String label = request.getParameter("label");
		
		if(action.equals("pin")) {
			int result = dao.pinNote(nid, uname, title);
		}else if(action.equals("unpin")) {
			int result = dao.unPinNote(nid, uname);
		}else if(action.equals("pinLabel")) {
			int result = dao.pinToLabel(nid, uname, label, title);
		}else if(action.equals("unPinLabel")) {
			int result = dao.unPinLabelNote(nid, uname, label);
		}
		
		response.sendRedirect("NotesDetails.jsp");
	}

}
