package com.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDAO loginDAO;

	@Override
	public void init() throws ServletException {
		loginDAO = new LoginDAO();
		super.init();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean result = false;

		result = loginDAO.validateCredentials(username, password);

		if (result) {
			request.getSession().setAttribute("username", username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("NotesDetails.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("Error.jsp");
		}

	}

}
