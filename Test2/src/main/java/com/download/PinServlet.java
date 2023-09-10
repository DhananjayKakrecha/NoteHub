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
	private PracticalsDAO pracDao;

	@Override
	public void init() throws ServletException {
		dao = new NotesDAO();
		pracDao = new PracticalsDAO();
		super.init();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("userName");
		String fileName = request.getParameter("fileName");
		String label = request.getParameter("label");
		String action = request.getParameter("action");
//		String search = request.getParameter("searchFilter");
		int result = 0;

//		if (action != null) {
//			if (action.equals("pin")) {
//				result = dao.pinNote(uname, fileName);
//				response.sendRedirect("SearchResult.jsp");
////				request.getSession().setAttribute("username",uname);
////				request.getSession().setAttribute("search", search);
//			} else if (action.equals("unpin")) {
//				result = dao.unPinNote(uname, fileName);
//				response.sendRedirect("SearchResult.jsp");
//			} else if(action.equals("pinlabel")) {
//				result = dao.pinToLabel(uname, fileName, label);
//				response.sendRedirect("SearchResult.jsp");
//			} else if(action.equals("unpinlabel")){
//				result = dao.unPinLabelNote(uname, label, fileName);
//				response.sendRedirect("SearchResult.jsp");
//			}
//		}

		if (action != null) {
			switch (action) {
			case "pin":
				result = dao.pinNote(uname, fileName);
				response.sendRedirect("SearchResult.jsp");
				break;
			case "unpin":
				result = dao.unPinNote(uname, fileName);
				response.sendRedirect("SearchResult.jsp");
				break;
			case "pinlabel":
				result = dao.pinToLabel(uname, fileName, label);
				response.sendRedirect("SearchResult.jsp");
				break;
			case "unpinlabel":
				result = dao.unPinLabelNote(uname, label, fileName);
				response.sendRedirect("SearchResult.jsp");
				break;
			case "unpinfromarchive":
				result = dao.unPinNote(uname, fileName);
				response.sendRedirect("Archive.jsp");
				break;
			case "pinlabelfromarchive":
				result = dao.pinToLabel(uname, fileName, label);
				response.sendRedirect("Archive.jsp");
				break;
			case "unpinlabelfromLabel":
				result = dao.unPinLabelNote(uname, label, fileName);
				response.sendRedirect("Batman.jsp");
				break;
			case "unpinfromarchivee":
				result = dao.unPinNote(uname, fileName);
				response.sendRedirect("Archivee.jsp");
				break;
			case "pinlabelfromarchivee":
				result = dao.pinToLabel(uname, fileName, label);
				response.sendRedirect("Archivee.jsp");
				break;
			case "pinlabelfromlabel":
				result = dao.pinToLabel(uname, fileName, label);
				response.sendRedirect("Batman.jsp");
				break;
			case "pinlabelfromfilterlabel":
				result = dao.pinToLabel(uname,fileName,label);
				response.sendRedirect("Batmann.jsp");
				break;
			case "unpinlabelfromfilterlabel":
				result = dao.unPinLabelNote(uname, label, fileName);
				response.sendRedirect("Batmann.jsp");
				break;
			}

//		RequestDispatcher dispatcher = request.getRequestDispatcher("NotesDetails.jsp");
//		dispatcher.forward(request, response);
		}

	}
}
