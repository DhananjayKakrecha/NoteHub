package com.download;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LabelPin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotesDAO dao;
	
	@Override
	public void init() throws ServletException {
		dao = new NotesDAO();
		super.init();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("userName");
		String file_name = request.getParameter("fileName");
		String label = request.getParameter("label");
		
		int result = dao.pinToLabel(uname, file_name, label);
		
		if(result == 1) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("Archive.jsp?username=" +uname);
			dispatcher.forward(request, response);
		}
	}

}
