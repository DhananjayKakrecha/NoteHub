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
<link rel="stylesheet" href="./stylelabel.css" />
<link rel="stylesheet" href="./style.css" />
</head>
<body>
	<%
	String uname = (String) request.getSession().getAttribute("username");
	uname.strip();

	String type = (String) request.getSession().getAttribute("type");
	type.strip();

	NotesDAO notesDao = new NotesDAO();
	List<String> labels = notesDao.getLabels(uname);
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
				<a href="./NotesDetails.jsp"><div class="section1 menu-section">
						<img src="./resources/icons8-idea-50.png" alt="Notes"
							height="32px" width="32px" />
						<p>Notes</p>
					</div></a>

				<%
				if (type.equals("teacher")) {
					List<String> subs = notesDao.getSubjects(uname);
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

				<a href="#"><div class="section2 menu-section"
						style="background-color: #feefc3">
						<img src="./resources/folder-plus.svg" alt="Notes" height="48px"
							width="24px" />
						<p>Label</p>
					</div></a> <a href="./Archive.jsp"><div class="section3 menu-section">
						<img src="./resources/archive.svg" alt="Notes" height="48px"
							width="24px" />
						<p>Archive</p>
					</div></a>
			</div>
			<div class="col-lg-9 label-col">
				<div class="label-section">
					<form action="label" method="post" class="label-form">
						<div class="create-label">
							<input type="text" placeholder="Create Label" name="label"
								maxlength="20" required />
						</div>
						<input type="hidden" name="username" value="<%=uname%>"> <input
							type="hidden" name="action" value="createLabel">
						<button type="submit" id="addlabel-button">
							<img src="./resources/send.svg" alt="" height="20px" width="20px" />
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="label-head">
			<p>LABELS</p>
		</div>
		<div class="row">
			<%
			for (String label : labels) {
			%>
			<div class="col-lg-3 labels">
				<div class="del-logo">
					<form action="label" method="post">
						<input type="hidden" name="username" value="<%=uname%>"> <input
							type="hidden" name="label" value="<%=label%>"> <input
							type="hidden" name="action" value="deleteLabel">
						<button type="submit" id="addlabel-button">
							<img src="./resources/trash.svg" alt="" height="22px"
								width="22px" />
						</button>
					</form>
				</div>
				<a href="search?label=<%=label%>"><p><%=label%></p></a>
			</div>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>
