package com.download;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Share extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotesDAO dao;
	
	@Override
	public void init() throws ServletException {
		dao = new NotesDAO();
		super.init();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sender = request.getParameter("username");
		String receiver = request.getParameter("receiver");
		String fileName= request.getParameter("fileName");
		
		boolean senderExists = dao.checkUsername(sender);
		
		if(senderExists) {
			int result = dao.share(sender, receiver, fileName);
			RequestDispatcher dispatcher = request.getRequestDispatcher("NotesDetails.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
