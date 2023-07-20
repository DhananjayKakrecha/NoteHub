package com.download;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotesDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new NotesDAO();
		super.init();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("userName");
		String fileName = request.getParameter("fileName");
		String label = request.getParameter("label");
		String action = request.getParameter("action");
		int result = 0;

		if (action != null) {
			if (action.equals("pin")) {
				result = dao.pinNote(uname, fileName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("NotesDetails.jsp");
				dispatcher.forward(request, response);
			} else if (action.equals("unpin")) {
				result = dao.unPinNote(uname, fileName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("NotesDetails.jsp");
				dispatcher.forward(request, response);
			} else {
				result = dao.unPinLabelNote(uname, label, fileName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("NotesDetails.jsp");
				dispatcher.forward(request, response);
			}
		}

	}

}
