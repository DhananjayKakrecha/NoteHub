package com.note;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoteDAO dao;
	
	@Override
	public void init() throws ServletException {
		dao = new NoteDAO();
		super.init();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sender = request.getParameter("sender");
		String receiver = request.getParameter("receiver");
		int nid = Integer.parseInt(request.getParameter("nid"));
		
		boolean userExists = dao.checkUsername(receiver);
		
		if(userExists) {
			int result = dao.shareNote(sender, receiver, nid);
			response.sendRedirect("NotesDetails.jsp");
		}else {
			response.sendRedirect("NotesDetails.jsp");
		}
	}

}
