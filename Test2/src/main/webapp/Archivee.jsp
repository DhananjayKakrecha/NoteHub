<%@page import="com.download.BooksDAO"%>
<%@page import="com.download.Books"%>
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
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="./style.css" />
    <link rel="stylesheet" href="./stylesnotes.css" />
  </head>
  <body>
    <%
	String uname = (String) request.getSession().getAttribute("username");
	String filter = (String) request.getSession().getAttribute("filter");
	
	NotesDAO dao = new NotesDAO();
	List<Notes> notes = dao.getFilterdNotes(uname, filter);
	List<String> lists = dao.getLabels(uname);
        boolean isLabelListEmpty = lists.isEmpty();    
	
	PracticalsDAO pracDao = new PracticalsDAO();
	List<Practicals> pracs = pracDao.getFilteredPracticals(filter, uname);
	
	BooksDAO bookDao = new BooksDAO();
	List<Books> books = bookDao.getFilteredbooks(filter, uname);
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
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="22px"
                  height="22px"
                  fill="currentColor"
                  class="bi bi-search"
                  viewBox="0 0 16 16"
                >
                  <path
                    d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
                  />
                </svg>
              </button>
              <input placeholder="Search" name="search" class="search-box" />
            </form>
          </div>
          <div class="col-lg-3 nav-items">
            <button type="button" class="btn btn-warning">Inbox</button>
            <div class="dropdown">
              <button class="btn btn-warning dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                Class
              </button>
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
                    >
                    <img src="./resources/lightbulb.svg" alt="Notes" height="48px"
                        width="24px" />
                    <p>Notes</p>
        </div></a>
        <a href="./Label.jsp">
            <div class="section2 menu-section">
                <img src="./resources/tag.svg" alt="Notes" height="48px"
                    width="24px" />
                <p>Label</p>
        
    </div></a>
    <%
				for (String list : lists) {
		    %>
			<a href="search?label=<%= list%>"><div class="section2 menu-section">
				<img src="./resources/tag.svg" alt="Notes" height="48px"
					width="24px">
				<p><%= list%></p>
			</div></a>
		<%
				}
				%>
    <a href="./Archive.jsp"><div class="section3 menu-section" style="background-color: #feefc3">
        <img src="./resources/archive.svg" alt="Notes" height="48px"
            width="24px" /> <p>Archive</p>
    </div></a>
          <div class="bookmark-section menu-section">
            <img src="./resources/bookmark-star.svg" alt="" height="25px" width="25px">
            <p>High Weithage</p>
          </div>  
          <div class="bookmark-section menu-section">
            <img src="./resources/bookmarks.svg" alt="" height="25px" width="25px">
            <p>Moderate Weithage</p>
          </div>  
        </div>
        <div class="col-lg-10">
				<div class="container">
					<div class="row">
						<div class="col-xxl-12 filter-section">
							<div class="filter-bar">
								<form action="search" method="post" class="search-form">
									<button type="submit" class="search-button">
										<img src="./resources/sort-down.svg" alt="" height="24px"
											width="24px" />
									</button>
									<input placeholder="Apply Filter" name="archivefilter"
										class="search-box" />
								</form>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xxl-12 section-head others">
							<p>REFERENCED NOTES</p>
						</div>
						<%
						for (Notes note : notes) {
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
										<input type="hidden" name="userName" value="<%=uname%>">
										<input type="hidden" name="fileName"
											value="<%=note.getFileName()%>">  <input type="hidden"
											name="action" value="unpinfromarchivee">
										<button type="submit" class="notebutton">
											<img src="./resources/pin-angle-fill.svg" height="20px"
												width="20px" />
										</button>
									</form>
								</div>
							</div>
							<p><%=note.getTopic()%></p>
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
								<%
								if (isLabelListEmpty == false) {
								%>
								<form action="pin" method="post" class="labelform">
									<input type="hidden" name="userName" value="<%=uname%>">
									<input type="hidden" name="fileName"
										value="<%=note.getFileName()%>"> <input type="hidden"
										name="action" value="pinlabelfromarchivee"> <select
										name="label" class="select-label">
										<%
										for (String list : lists) {
											boolean result = dao.isLabelled(uname, note.getFileName(), list);

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
					<div class="section-head">
						<p>PRACTICALS</p>
					</div>
					<div class="row">
						<%
						for (Practicals prac : pracs) {
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
										<input type="hidden" name="userName" value="<%=uname%>">
										<input type="hidden" name="fileName"
											value="<%=prac.getFileName()%>"><input type="hidden"
											name="action" value="unpinfromarchivee">
										<button type="submit" class="notebutton">
											<img src="./resources/pin-angle-fill.svg" height="20px"
												width="20px" />
										</button>
									</form>
								</div>
							</div>
							<p><%=prac.getTopic()%></p>
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
									<%
									if (isLabelListEmpty == false) {
									%>
								</form>
								<form action="pin" method="post" class="labelform">
									<input type="hidden" name="userName" value="<%=uname%>">
									<input type="hidden" name="fileName"
										value="<%=prac.getFileName()%>"> <input type="hidden"
										name="action" value="pinlabelfromarchivee"> <select
										name="label" class="select-label">
										<%
										for (String list : lists) {
											boolean result = dao.isLabelled(uname, prac.getFileName(), list);

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
					<div class="section-head">
						<p>BOOKS</p>
					</div>
					<div class="row">
						<%
						for (Books book : books) {
						%>
						<div class="col-lg-3 note">
							<div class="note-head">
								<div class="pinlogo">
									<form method="post" action="pin">
										<input type="hidden" name="userName" value="<%=uname%>">
										<input type="hidden" name="fileName"
											value="<%=book.getFileName()%>"> <input type="hidden"
											name="action" value="unpinfromarchivee">
										<button type="submit" class="notebutton">
											<img src="./resources/pin-angle-fill.svg" height="20px"
												width="20px" />
										</button>
									</form>
								</div>
							</div>
							<p><%=book.getFileName()%></p>
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
								<%
								if (isLabelListEmpty == false) {
								%>
								<form action="pin" method="post" class="labelform">
									<input type="hidden" name="userName" value="<%=uname%>">
									<input type="hidden" name="fileName"
										value="<%=book.getFileName()%>"> <input type="hidden"
										name="action" value="pinlabelfromarchivee"> <select
										name="label" class="select-label">
										<%
										for (String list : lists) {
											boolean result = dao.isLabelled(uname, book.getFileName(), list);

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
				</div>
			</div>
		</div>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
			crossorigin="anonymous"></script>
</body>
</html>
        