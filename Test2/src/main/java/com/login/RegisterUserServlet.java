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
		String type = "student";
		boolean action = true;

		if (username.toLowerCase().equals("notes") || username.toLowerCase().equals("note")
				|| username.toLowerCase().equals("practicals") || username.toLowerCase().equals("books")
				|| username.toLowerCase().equals("sharednotes") || username.toLowerCase().equals("labels")) {
			action = false;
		}

		if (!dao.checkUsername(username) && action) {
			// Create a new Registration object
			Registration registration = new Registration(username, password, fullName, email, type);

			// Save the registration data to the database
			dao.saveRegistration(registration);
			boolean result = dao.generateUser(username);
			System.out.println(result);
			if (!result) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			response.sendRedirect("Error.jsp");
		}

	}

}
