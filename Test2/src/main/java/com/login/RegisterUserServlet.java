package com.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegistrationDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new RegistrationDAO();
		super.init();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullname");
		String email = request.getParameter("email");
		String type = "user";

		if(!dao.checkUsername(username)) {
			// Create a new Registration object
			Registration registration = new Registration(username, password, fullName, email, type);

			// Save the registration data to the database
			dao.saveRegistration(registration);
			boolean result = dao.generateUser(username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("Error.jsp");
		}
		
		
	}

}
