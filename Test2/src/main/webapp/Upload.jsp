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
<link rel="stylesheet" href="./upload.css" />
<link rel="stylesheet" href="./style.css" />
</head>
<body>
	<%
	String username = (String) request.getSession().getAttribute("username");
	username.strip();

	String type = (String) request.getSession().getAttribute("type");
	type.strip();

	NotesDAO dao = new NotesDAO();
	List<String> subs = dao.getSubjects(username);
	List<String> lists = dao.getLabels(username);
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
							data-bs-toggle="dropdown" aria-expanded="false">
							Class</button>
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
					List<String> subsj = dao.getSubjects(username);
					for (String sub : subsj) {
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
			<div class="col-lg-9 uploadNote-col">
				<div class="uploadnote-section">
					<form class="row g-3" action="upload" method="post"
						enctype="multipart/form-data">
						<div class="col-12">
							<label for="topic" class="form-label">Topic</label> <input
								type="text" class="form-control" id="inputAddress"
								placeholder="Enter Topic" name="topic" required />
						</div>
						<div class="col-md-6">
							<label for="exampleDataList" class="form-label">Subject</label>
							<input class="form-control" list="datalistOptions"
								id="exampleDataList"
								placeholder="Subject Name in short (Ex OS,INS)" name="subject"
								required />
							<datalist id="datalistOptions">
								<%
								for (String sub : subs) {
								%>
								<option value="<%=sub%>"></option>
								<%
								}
								%>
							</datalist>
						</div>
						<div class="col-md-6">
							<label for="notesType" class="form-label">Notes Type</label> <select
								id="inputState" class="form-select" name="notetype" required>
								<option selected>Notes</option>
								<option>Practicals</option>
								<option>Books</option>
							</select>
						</div>
						<div class="col-md-4">
							<label for="weightage" class="form-label">Weightage</label> <select
								id="inputState" class="form-select" name="weightage" required>
								<option selected>High</option>
								<option>Moderate</option>
								<option>Low</option>
								<option>None(For Books)</option>
							</select>
						</div>
						<div class="col-md-4">
							<label for="unit" class="form-label">Unit</label> <select
								id="inputState" class="form-select" name="unit" required>
								<option selected>Unit I</option>
								<option>Unit II</option>
								<option>Unit III</option>
							</select>
						</div>
						<div class="col-12">
							<label for="formFile" class="form-label">Upload File</label> <input
								class="form-control" type="file" id="formFile" name="file"
								required />
						</div>
						<div class="div">
							<input type="hidden" name="action" value="uploadNotes">
							<button type="submit" id="addNote-button">
								<img src="./resources/send.svg" alt="" height="20px"
									width="20px" />
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
