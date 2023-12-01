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
		String action = request.getParameter("action");
		String uname = request.getParameter("username");
		
		String type = (String)request.getSession().getAttribute("type");
		
		if(action.equals("note") && type.equals("teacher")) {
			int result = dao.deleteNote(nid);
			int vcNotesdel = dao.deleteVCNotes(nid, uname);
			response.sendRedirect("NotesDetails.jsp");
		}else if(action.equals("vcnote")  && type.equals("teacher")) {
			int vid = Integer.parseInt(request.getParameter("vid"));
			int vcNoteDel = dao.deleteVCNote(vid, uname);
			response.sendRedirect("NotesDetails.jsp");
		} else if(action.equals("note") && type.equals("student")) {
			int result = dao.deleteNote(nid);
			int vcNotesdel = dao.deleteVCNotes(nid, uname);
			response.sendRedirect("NotesDetails.jsp");
		} else if(action.equals("vcnote")  && type.equals("student")) {
			int vid = Integer.parseInt(request.getParameter("vid"));
			int vcNoteDel = dao.deleteVCNote(vid, uname);
			response.sendRedirect("NotesDetails.jsp");
		}
	}

}
