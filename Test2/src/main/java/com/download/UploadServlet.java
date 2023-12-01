package com.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
		maxFileSize = 1024 * 1024 * 1000, // 1 GB
		maxRequestSize = 1024 * 1024 * 1000) // 1 GB
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotesDAO dao;
	private PracticalsDAO pracDao;
	private BooksDAO bookDao;
	
	@Override
	public void init() throws ServletException {
		dao = new NotesDAO();
		pracDao = new PracticalsDAO();
		bookDao = new BooksDAO();
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if(action.equals("unotes")) {
			int notesId = Integer.parseInt(req.getParameter("notesId"));
			req.getSession().setAttribute("utype", action);
			req.getSession().setAttribute("nobject", dao.getNotesById(notesId));
			RequestDispatcher dispatcher = req.getRequestDispatcher("UpdateNotes.jsp");
			dispatcher.forward(req, resp);
		}else if(action.equals("upracs")) {
			int pid = Integer.parseInt(req.getParameter("pid"));
			req.getSession().setAttribute("utype", action);
			req.getSession().setAttribute("pobject", pracDao.getpracsById(pid));
			RequestDispatcher dispatcher = req.getRequestDispatcher("UpdateNotes.jsp");
			dispatcher.forward(req, resp);
		}else if(action.equals("ubooks")) {
			int bid = Integer.parseInt(req.getParameter("bid"));
			req.getSession().setAttribute("utype", action);
			req.getSession().setAttribute("bobject", bookDao.getbooksById(bid));
			RequestDispatcher dispatcher = req.getRequestDispatcher("UpdateNotes.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("uploadNotes")) {
			String uname = (String) request.getSession().getAttribute("username");
			uname.strip();
			String subname = request.getParameter("subject");
			String topic = request.getParameter("topic");
			String unit = request.getParameter("unit");
			String weightage = request.getParameter("weightage");
			String notetype = request.getParameter("notetype");

			Part filePart = request.getPart("file");
			String fileName = filePart.getSubmittedFileName();
			
			int uploadResult = dao.uploadNote(topic, uname, unit, weightage, notetype, fileName, subname);
			
			response.setContentType("text/plain");
			try {
				
				String applicationPath = getServletContext().getRealPath("");
				String res = "resources";
				String uploadPath = applicationPath + res + File.separator + subname;
				File dir = new File(uploadPath);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				String path = subname + File.separator + fileName;
				System.out.println("fileName: " + fileName);
				System.out.println("Path: " + uploadPath);
				InputStream is = filePart.getInputStream();
				Files.copy(is, Paths.get(uploadPath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);

				System.out.println("Succesfully Uploaded " + path);

			} catch (IOException e) {
				System.out.println(e);
			}
			
			response.sendRedirect("NotesDetails.jsp");

		}else if(action.equals("updateNotes")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String subname = request.getParameter("subject");
			String topic = request.getParameter("topic");
			String unit = request.getParameter("unit");
			String weightage = request.getParameter("weightage");
			String notetype = request.getParameter("notetype");
			
			int uploadResult = dao.updateUploadNote(id, topic, unit, weightage, notetype, subname);
						
			response.sendRedirect("Subject.jsp");
		}
	}

}
