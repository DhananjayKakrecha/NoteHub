package com.download;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LabelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotesDAO dao;
	
	@Override
	public void init() throws ServletException {
		dao = new NotesDAO();
		super.init();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String label = request.getParameter("labelName");
		
		int result = dao.createLabel(username, label);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("NotesDetails.jsp");
		dispatcher.forward(request, response);
	}

}
