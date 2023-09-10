package com.note;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RollBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NoteDAO dao;
    
    @Override
    public void init() throws ServletException {
    	dao = new NoteDAO();
    	super.init();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		int nid = Integer.parseInt(request.getParameter("nid"));
		int vid = Integer.parseInt(request.getParameter("vid"));
		int branchFrom = Integer.parseInt(request.getParameter("branchFrom"));

		int result = dao.RollBackVC(vid, branchFrom, nid, uname);
		
		response.sendRedirect("NotesDetails.jsp");
	}

}
