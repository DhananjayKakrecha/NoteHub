<%@page import="java.util.List"%>
<%@page import="com.download.NotesDAO"%>
<%@page import="com.note.VersionControl"%>
<%@page import="com.note.NoteDAO"%>
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
<link rel="stylesheet" href="./addNote.css" />
<link rel="stylesheet" href="./style.css" />
</head>
<body>
	<%
	String uname = (String) request.getSession().getAttribute("username");
	uname.strip();

	int nid = Integer.parseInt(request.getParameter("nid"));
	int vid = Integer.parseInt(request.getParameter("vid"));

	NoteDAO noteDao = new NoteDAO();
	VersionControl vcnote = noteDao.getNoteVC(nid, vid, uname);

	NotesDAO dao = new NotesDAO();
	List<String> lists = dao.getLabels(uname);

	boolean result = noteDao.getNextVid(vid, nid, uname);
	int maxVid = noteDao.getVid(nid, uname);
	%>

	<header>
		<div class="contianer-fluid nav-bar">
			<div class="row nav-row">
				<div class="col-lg-2 col-md-2 nav-logo">
					<div class="logo"></div>
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
					<button type="button" class="btn btn-warning">Inbox</button>
					<div class="dropdown">
						<button class="btn btn-warning dropdown-toggle" type="button"
							data-bs-toggle="dropdown" aria-expanded="false">
							Class</button>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">FY</a></li>
							<li><a class="dropdown-item" href="#">CS</a></li>
							<li><a class="dropdown-item" href="#">TY</a></li>
						</ul>
					</div>
					<button type="button" class="btn btn-warning">Log Out</button>
				</div>
			</div>
		</div>
	</header>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-2">
				<a href="search">
					<div class="section1 menu-section"
						style="background-color: #feefc3">
						<img src="./resources/lightbulb.svg" alt="Notes" height="48px"
							width="24px" />
						<p>Notes</p>
					</div>
				</a> <a href="./Label.jsp">
					<div class="section2 menu-section">
						<img src="./resources/tag.svg" alt="Notes" height="48px"
							width="24px" />
						<p>Label</p>

					</div>
				</a>
				<%
				for (String list : lists) {
				%>
				<a href="search?label=<%=list%>"><div
						class="section2 menu-section">
						<img src="./resources/tag.svg" alt="Notes" height="48px"
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
			</div>
			<div class="col-lg-9 addNote-col">
				<div class="addnote-section">
					<div class="addnote-form">
						<div class="note-title">
							<input type="text" name="title" placeholder="Title"
								value="<%=vcnote.getTitle()%>" />
						</div>
						<div class="note-description">
							<textarea name="desc" placeholder="Description" rows="15"
								cols="100"><%=vcnote.getDescription()%></textarea>
						</div>
						<div class="verisonbuttons">
							<%
							if ((!result && (maxVid == vid)) && vcnote.getBranchFrom() == 0) {
							%>
							<form action="deleteNote?username=<%=uname%>" method="post">
								<input type="hidden" name="nid" value="<%=nid%>"> <input
									type="hidden" name="vid" value="<%=vid%>"> <input
									type="hidden" name="action" value="vcnote">
								<button type="submit" class="btn btn-warning version-button">Delete</button>
							</form>
							<%
							} else if (maxVid == vid) {
							%>
							<form action="rollback?username=<%=uname%>" method="post">
								<input type="hidden" name="branchFrom"
									value="<%=vcnote.getBranchFrom()%>"> <input
									type="hidden" name="nid" value="<%=nid%>"> <input
									type="hidden" name="vid" value="<%=vid%>">
								<button type="submit" class="btn btn-warning version-button">Rollback</button>
							</form>
							<%
							} else {
							}
							%>
							<form action="addNote?username=<%=uname%>" method="post">
								<input type="hidden" name="title" value="<%=vcnote.getTitle()%>">
								<input type="hidden" name="desc"
									value="<%=vcnote.getDescription()%>"> <input
									type="hidden" name="action" value="add">
								<button type="submit" class="btn btn-warning version-button">Copy Note</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
