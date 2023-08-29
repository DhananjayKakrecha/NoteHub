package com.note;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NoteDAO dao;
    
    @Override
    public void init() throws ServletException {
    	dao = new NoteDAO();
    	super.init();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nid = Integer.parseInt(request.getParameter("nid"));
		
		int result = dao.deleteNote(nid);
		
		response.sendRedirect("NotesDetails.jsp");
	}

}
