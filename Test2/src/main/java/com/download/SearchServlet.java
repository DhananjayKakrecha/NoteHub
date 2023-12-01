package com.download;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String label = req.getParameter("label");
		String subname = req.getParameter("subname");

		if (label != null) {
			req.getSession().setAttribute("label", label);
			RequestDispatcher dispatcher = req.getRequestDispatcher("Batman.jsp");
			dispatcher.forward(req, resp);
		} else if (subname != null) {
			req.getSession().setAttribute("subname", subname);
			RequestDispatcher dispatcher = req.getRequestDispatcher("Subject.jsp");
			dispatcher.forward(req, resp);
		} 
//		super.doGet(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String archiveFilter = request.getParameter("archivefilter");
		String labelFilter = request.getParameter("labelfilter");
		String search = request.getParameter("search");
		String noteFilter = request.getParameter("notefilter");
		String nid = request.getParameter("nid");

//		System.out.println(label);

		if (search != null) {
			request.getSession().setAttribute("search", search);
			RequestDispatcher dispatcher = request.getRequestDispatcher("SearchResult.jsp");
			dispatcher.forward(request, response);
		} else if (archiveFilter != null) {
			request.getSession().setAttribute("filter", archiveFilter);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Archivee.jsp");
			dispatcher.forward(request, response);
		} else if (labelFilter != null) {
			request.getSession().setAttribute("filter", labelFilter);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Batmann.jsp");
			dispatcher.forward(request, response);
		} else if (noteFilter != null) {
			request.getSession().setAttribute("filter", noteFilter);
			RequestDispatcher dispatcher = request.getRequestDispatcher("NotesDetail.jsp");
			dispatcher.forward(request, response);
		} else if (nid != null) {
			request.getSession().setAttribute("nid", nid);
			RequestDispatcher dispatcher = request.getRequestDispatcher("VersionControl.jsp");
			dispatcher.forward(request, response);
		}
	}

}
