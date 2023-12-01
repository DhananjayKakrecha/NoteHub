<%@page import="com.note.NoteDAO"%>
<%@page import="com.note.Note"%>
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
<link rel="stylesheet" href="./stylenote.css" />
</head>
<body>
	<%
	String username = (String) request.getSession().getAttribute("username");
	username.strip();

	String type = (String) request.getSession().getAttribute("type");
	type.strip();

	NotesDAO dao = new NotesDAO();
	List<String> lists = dao.getLabels(username);
	boolean isLabelListEmpty = lists.isEmpty();

	NoteDAO notesDao = new NoteDAO();
	List<Note> notes = notesDao.getPinnedNotes(username);
	List<Note> unotes = notesDao.getNote(username);
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
					<div class="section1 menu-section"
						style="background-color: #feefc3">
						<img src="./resources/icons8-idea-50.png" alt="Notes"
							height="32px" width="32px" />
						<p>Notes</p>
					</div>
				</a>

				<%
				if (type.equals("teacher")) {
					List<String> subs = dao.getSubjects(username);
					for (String sub : subs) {
				%>

				<a href="search?subname=<%=sub%>">
					<div class="sub-section menu-section">
						<img
							src="./resources/letters/<%=sub.toLowerCase().charAt(0)%>.png"
							height="32px" width="32px" />
						<p><%=sub%></p>
					</div>
				</a>

				<%
				}
				}
				%>

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
			</div>
			<div class="col-lg-9">
				<div class="container">
					<div class="row">
						<div class="col-xxl-12 filter-section">
							<div class="filter-bar">
								<form action="search" method="post" class="search-form">
									<button type="submit" class="search-button">
										<img src="./resources/sort-down.svg" alt="" height="24px"
											width="24px" />
									</button>
									<input placeholder="Apply Filter" name="notefilter"
										class="search-box" />
								</form>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xxl-12 section-head">
							<p>NOTES</p>
						</div>
						<%
						for (Note note : notes) {
							Integer nid = (Integer) note.getId();
						%>
						<div class="col-lg-4 note">
							<a
								href="UNote.jsp?username=<%=username%>&nid=<%=nid.toString()%>"><div
									class="pinlogo">
									<form action="pinNote?username=<%=username%>" method="post">
										<input type="hidden" name="title" value="<%=note.getTitle()%>">
										<input type="hidden" name="nid" value="<%=note.getId()%>">
										<input type="hidden" name="action" value="unpin">
										<button type="submit" class="notebutton">
											<img src="./resources/pin-angle-fill.svg" height="20px"
												width="20px" />
										</button>
									</form>
								</div>
								<p><%=note.getTitle()%></p>
								<div class="note-labels"></div></a>
							<div class="operation">
								<form action="search" method="post">
									<input type="hidden" name="nid" value="<%=note.getId()%>">
									<button type="submit" class="notebutton">
										<img src="./resources/share.svg" alt="" height="15px"
											width="15px" />
									</button>
								</form>
								<form action="Share.jsp?username=<%=username%>" method="post">
									<input type="hidden" name="nid" value="<%=note.getId()%>">
									<button type="submit" class="notebutton">
										<img src="./resources/arrow-90deg-right.svg" alt=""
											height="15px" width="15px" />
									</button>
								</form>
								<form action="deleteNote?username=<%=username%>" method="post">
									<input type="hidden" name="nid" value="<%=note.getId()%>">
									<input type="hidden" name="action" value="note">
									<button type="submit" class="notebutton">
										<img src="./resources/trash.svg" alt="" height="15px"
											width="15px" />
									</button>
								</form>
								<%
								if (isLabelListEmpty == false) {
								%>
								<form action="pinNote" method="post" class="labelform">
									<input type="hidden" name="username" value="<%=username%>">
									<input type="hidden" name="nid" value="<%=note.getId()%>">
									<input type="hidden" name="title" value="<%=note.getTitle()%>">
									<input type="hidden" name="action" value="pinLabel"> <select
										name="label" class="select-label">
										<%
										for (String list : lists) {
											boolean result = notesDao.isLabelled(username, note.getId(), list);

											if (!result) {
										%>
										<option value="<%=list%>"><%=list%></option>
										<%
										}
										}
										%>
									</select>
									<button type="submit" class="notebutton select-button">
										<img src="./resources/plus-circle.svg" alt="Add" height="15px"
											width="15px" />
									</button>
								</form>
								<%
								} else {
								%>
								<%
								}
								%>
							</div>
						</div>
						<%
						}
						%>
					</div>
					<div class="row">
						<div class="col-xxl-12 section-head others">
							<p>OTHERS</p>
						</div>
						<%
						for (Note note : unotes) {
							Integer nid = (Integer) note.getId();

							boolean isPinned = notesDao.isPinned(username, nid);

							if (!isPinned) {
						%>
						<div class="col-lg-4 note">
							<a
								href="UNote.jsp?username=<%=username%>&nid=<%=nid.toString()%>"><div
									class="pinlogo">
									<form action="pinNote?username=<%=username%>" method="post">
										<input type="hidden" name="title" value="<%=note.getTitle()%>">
										<input type="hidden" name="nid" value="<%=note.getId()%>">
										<input type="hidden" name="action" value="pin">
										<button type="submit" class="notebutton">
											<img src="./resources/pin-angle.svg" height="20px"
												width="20px" />
										</button>
									</form>
								</div>
								<p><%=note.getTitle()%></p>
								<div class="note-labels"></div></a>
							<div class="operation">
								<button type="button" class="notebutton">
									<a
										href="VersionControl.jsp?username=<%=username%>&nid=<%=nid.toString()%>"><img
										src="./resources/share.svg" alt="" height="15px" width="15px" /></a>
								</button>
								<form action="Share.jsp?username=<%=username%>" method="post">
									<input type="hidden" name="nid" value="<%=note.getId()%>">
									<button type="submit" class="notebutton">
										<img src="./resources/arrow-90deg-right.svg" alt=""
											height="15px" width="15px" />
									</button>
								</form>
								<form action="deleteNote?username=<%=username%>" method="post">
									<input type="hidden" name="nid" value="<%=note.getId()%>">
									<input type="hidden" name="action" value="note">
									<button type="submit" class="notebutton">
										<img src="./resources/trash.svg" alt="" height="15px"
											width="15px" />
									</button>
								</form>
								<%
								if (isLabelListEmpty == false) {
								%>
								<form action="pinNote" method="post" class="labelform">
									<input type="hidden" name="username" value="<%=username%>">
									<input type="hidden" name="nid" value="<%=note.getId()%>">
									<input type="hidden" name="title" value="<%=note.getTitle()%>">
									<input type="hidden" name="action" value="pinLabel"> <select
										name="label" class="select-label">
										<%
										for (String list : lists) {
											boolean result = notesDao.isLabelled(username, note.getId(), list);

											if (!result) {
										%>
										<option value="<%=list%>"><%=list%></option>
										<%
										}
										}
										%>
									</select>
									<button type="submit" class="notebutton select-button">
										<img src="./resources/plus-circle.svg" alt="Add" height="15px"
											width="15px" />
									</button>
								</form>
								<%
								} else {
								%>
								<%
								}
								%>
							</div>
						</div>
						<%
						}
						}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
	if (type.equals("teacher")) {
	%>
	<div class="upload-note">
		<button type="button" class="btn btn-warning notes-buttons">
			<a href="./Upload.jsp"><img src="./resources/upload.svg" alt="" /></a>
		</button>
	</div>
	<%
	}
	%>
	<div class="add-note">
		<button type="button" class="btn btn-warning notes-buttons">
			<a href="./AddNote.jsp"><img src="./resources/plus-lg.svg" alt="" /></a>
		</button>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>
