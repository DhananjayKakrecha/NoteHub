<%@page import="com.download.Books"%>
<%@page import="com.download.BooksDAO"%>
<%@page import="com.download.Practicals"%>
<%@page import="com.download.PracticalsDAO"%>
<%@page import="com.download.Notes"%>
<%@page import="java.util.List"%>
<%@page import="com.download.NotesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>NoteHub</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="./style.css" />
<link rel="stylesheet" href="./stylesnotes.css" />
</head>
<body>
	<%
	String uname = (String) request.getSession().getAttribute("username");
	uname.strip();

	String subname = (String) request.getSession().getAttribute("subname");
	subname.strip();

	NotesDAO dao = new NotesDAO();
	List<Notes> notes = dao.getNotesFromSubject(subname, uname);
	List<String> lists = dao.getLabels(uname);
	List<String> subs = dao.getSubjects(uname);

	PracticalsDAO pracDao = new PracticalsDAO();
	List<Practicals> pracs = pracDao.getPracticalsFromSubject(subname, uname);

	BooksDAO bookDao = new BooksDAO();
	List<Books> books = bookDao.getBooksFromSubject(subname, uname);
	%>
	<header>
		<div class="contianer-fluid nav-bar">
			<div class="row nav-row">
				<div class="col-lg-2 col-md-2 nav-logo">
					<div class="logo">
						<img src="./resources/NoteHubIcon1.png" alt="" height="38px">
					</div>
					<p>NoteHub</p>
				</div>
				<div class="col-lg-6 col-md-10 search-bar">
					<form action="search" method="post" class="search-form">
						<button type="submit" class="search-button">
							<svg xmlns="http://www.w3.org/2000/svg" width="22px"
								height="22px" fill="currentColor" class="bi bi-search"
								viewBox="0 0 16 16">
                  <path
									d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                </svg>
						</button>
						<input placeholder="Search" name="search" class="search-box" />
					</form>
				</div>
				<div class="col-lg-3 nav-items">
					<button type="button" class="btn btn-warning">
						<a href="./Inbox.jsp">Inbox</a>
					</button>
					<div class="dropdown">
						<button class="btn btn-warning dropdown-toggle" type="button"
							data-bs-toggle="dropdown" aria-expanded="false">Class</button>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">FY</a></li>
							<li><a class="dropdown-item" href="#">CS</a></li>
							<li><a class="dropdown-item" href="#">TY</a></li>
						</ul>
					</div>
					<button type="button" class="btn btn-warning">
						<a href="./Login.jsp">Log Out</a>
					</button>
				</div>
			</div>
		</div>
	</header>
	<div class="container-fluid hero">
		<div class="row">
			<div class="col-lg-3">
				<a href="./NotesDetails.jsp">
					<div class="section1 menu-section">
						<img src="./resources/icons8-idea-50.png" alt="Notes"
							height="32px" width="32px" />
						<p>Notes</p>
					</div>
				</a>

				<%
				for (String sub : subs) {
					if(sub.equals(subname)){
				%>

				<a href="search?subname=<%=sub%>"><div
						class="sub-section menu-section" style="background-color: #feefc3">
						<img
							src="./resources/letters/<%=sub.toLowerCase().charAt(0)%>.png"
							height="32px" width="32px" />
						<p><%=sub%></p>
					</div></a>

				<%
				}else{
				%>
				<a href="search?subname=<%=sub%>"><div
						class="sub-section menu-section">
						<img
							src="./resources/letters/<%=sub.toLowerCase().charAt(0)%>.png"
							height="32px" width="32px" />
						<p><%=sub%></p>
					</div></a>
				<% }
					} %>
				
				
				<a href="./Label.jsp">
					<div class="section2 menu-section">
						<img src="./resources/folder-plus.svg" alt="Notes" height="48px"
							width="24px" />
						<p>Label</p>

					</div>
				</a>
				<%
				for (String list : lists) {
				%>
				<a href="search?label=<%=list%>"><div
						class="section2 menu-section">
						<img src="./resources/folder2.svg" alt="Notes" height="48px"
							width="24px">
						<p><%=list%></p>
					</div></a>
				<%
				}
				%>
				<a href="./Archive.jsp"><div class="section3 menu-section">
						<img src="./resources/archive.svg" alt="Notes" height="48px"
							width="24px" />
						<p>Archive</p>
					</div></a>
				<div class="bookmark-section menu-section">
					<img src="./resources/bookmark-star.svg" alt="" height="25px"
						width="25px">
					<p>High Weithage</p>
				</div>
				<div class="bookmark-section menu-section">
					<img src="./resources/bookmarks.svg" alt="" height="25px"
						width="25px">
					<p>Moderate Weithage</p>
				</div>
			</div>
			<div class="col-lg-9">
				<div class="container">
					<div class="section-head">
						<p>NOTES</p>
					</div>
					<div class="row">
						<%
						for (Notes note : notes) {
							Integer notesId = (Integer) note.getNid();
						%>
						<div class="col-lg-3 note">
							<div class="note-head">
								<%
								if (note.getWeightage().equals("High")) {
								%>
								<div class="bookmark">
									<img src="./resources/bookmark-star.svg" alt="" height="25px"
										width="25px">
								</div>
								<%
								} else if (note.getWeightage().equals("Moderate")) {
								%>
								<div class="bookmark">
									<img src="./resources/bookmark-check.svg" alt="" height="25px"
										width="25px">
								</div>
								<%
								} else {
								%>
								<div class="bookmark">
									<img src="./resources/bookmark-dash.svg" alt="" height="25px"
										width="25px">
								</div>
								<%
								}
								%>
								<div class="pinlogo">

									<form method="post" action="pin">
										<input type="hidden" name="notesId" value="<%=note.getNid()%>">
										<input type="hidden" name="action" value="delnotefromsub">
										<button type="submit" class="notebutton">
											<img src="./resources/x-circle.svg" height="20px"
												width="20px" />
										</button>
									</form>
								</div>
							</div>
							<a href="upload?action=unotes&notesId=<%=notesId.toString()%>"><p><%=note.getTopic()%></p>
								<span><%=note.getSubName()%></span> <span><%=note.getUnit()%></span>
								<div class="operation">
									<form action="download" method="post">
										<input type="hidden" name="subName"
											value="<%=note.getSubName()%>"> <input type="hidden"
											name="fileName" value="<%=note.getFileName()%>">
										<button type="submit" class="notebutton">
											<img src="./resources/download.svg" alt="" height="15px"
												width="15px" />
										</button>
									</form>
								</div> </a>
						</div>
						<%
						}
						%>
					</div>
					<div class="section-head">
						<p>PRACTICALS</p>
					</div>
					<div class="row">
						<%
						for (Practicals prac : pracs) {
							Integer pid = (Integer) prac.getPid();
						%>
						<div class="col-lg-3 note">
							<div class="note-head">
								<%
								if (prac.getWeightage().equals("High")) {
								%>
								<div class="bookmark">
									<img src="./resources/bookmark-star.svg" alt="" height="25px"
										width="25px">
								</div>
								<%
								} else if (prac.getWeightage().equals("Moderate")) {
								%>
								<div class="bookmark">
									<img src="./resources/bookmark-check.svg" alt="" height="25px"
										width="25px">
								</div>
								<%
								} else {
								%>
								<div class="bookmark">
									<img src="./resources/bookmark-dash.svg" alt="" height="25px"
										width="25px">
								</div>
								<%
								}
								%>
								<div class="pinlogo">

									<form method="post" action="pin">
										<input type="hidden" name="pid" value="<%=prac.getPid()%>">
										<input type="hidden" name="action" value="delpracfromsub">
										<button type="submit" class="notebutton">
											<img src="./resources/x-circle.svg" height="20px"
												width="20px" />
										</button>
									</form>
								</div>
							</div>
							<a href="upload?action=upracs&pid=<%=pid.toString()%>"><p><%=prac.getTopic()%></p>
								<span><%=prac.getSubName()%></span> <span><%=prac.getUnit()%></span>
								<div class="operation">
									<form action="download" method="post">
										<input type="hidden" name="subName"
											value="<%=prac.getSubName()%>"> <input type="hidden"
											name="fileName" value="<%=prac.getFileName()%>">
										<button type="submit" class="notebutton">
											<img src="./resources/download.svg" alt="" height="15px"
												width="15px" />
										</button>
									</form>
								</div> </a>
						</div>
						<%
						}
						%>
					</div>
					<div class="section-head">
						<p>BOOKS</p>
					</div>
					<div class="row">
						<%
						for (Books book : books) {
							Integer bid = (Integer) book.getBid();
						%>
						<div class="col-lg-3 note">
							<div class="note-head">
								<div class="pinlogo">
									<form method="post" action="pin">
										<input type="hidden" name="bid" value="<%=book.getBid()%>">
										<input type="hidden" name="action" value="delbookfromsub">
										<button type="submit" class="notebutton">
											<img src="./resources/x-circle.svg" height="20px"
												width="20px" />
										</button>
									</form>
								</div>
							</div>
							<a href="upload?action=ubooks&bid=<%=bid.toString()%>"><p><%=book.getFileName()%></p>
								<span><%=book.getSubName()%></span>
								<div class="operation">
									<form action="download" method="post">
										<input type="hidden" name="subName"
											value="<%=book.getSubName()%>"> <input type="hidden"
											name="fileName" value="<%=book.getFileName()%>">
										<button type="submit" class="notebutton">
											<img src="./resources/download.svg" alt="" height="15px"
												width="15px" />
										</button>
									</form>
								</div> </a>
						</div>
						<%
						}
						%>
					</div>
				</div>
			</div>
		</div>
		<div class="upload-note">
			<button type="button" class="btn btn-warning notes-buttons">
				<a href="./UploadFSubject.jsp?subname=<%=subname%>"><img
					src="./resources/upload.svg" alt="" /></a>
			</button>
		</div>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
			crossorigin="anonymous"></script>
</body>
</html>