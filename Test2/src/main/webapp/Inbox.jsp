<%@page import="com.note.Note"%>
<%@page import="java.util.List"%>
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
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="./style.css" />
    <link rel="stylesheet" href="./versionnotestyle.css" />
  </head>
  <body>
    <% String uname = (String) request.getSession().getAttribute("username");
uname.strip(); 
	NoteDAO noteDao = new NoteDAO();
	List<Note> notes = noteDao.getSharedNote(uname);
        int count = 1;
%>
    <header>
      <div class="contianer-fluid nav-bar">
        <div class="row nav-row">
          <div class="col-lg-2 col-md-2 nav-logo">
            <div class="logo"></div>
            <p>NoteHub</p>
          </div>
          <div class="col-lg-6 col-md-10 search-bar">
            <form action="" method="post" class="search-form">
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
              <input placeholder="Search" name="searchbox" class="search-box" />
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
          <div class="section1 menu-section" style="background-color: #FEEFC3;">
            <img src="./resources/lightbulb.svg" alt="Notes" height="48px" width="24px">
            <a href="#"><p>Notes</p></a>
          </div>
          <div class="section2 menu-section">
            <img src="./resources/tag.svg" alt="Notes" height="48px" width="24px">
            <a href="#"><p>Label</p></a>
          </div>
          <div class="section3 menu-section">
            <img src="./resources/archive.svg" alt="Notes" height="48px" width="24px">
            <a href="#"><p>Archive</p></a>
          </div>  
        </div>
        <div class="col-lg-9">          
          <div class="container">
            <div class="section-head">
              <p>NOTES INBOX</p>
            </div>  
            <div class="row">
                <% for(Note note : notes){ 
                    Integer sid = (Integer) note.getId();
                %>
              <div class="col-lg-3 note">
                <a href="SharedNote.jsp?sid=<%= sid.toString()%>"><div class="versioncount">
                        <button type="submit" class="notebutton versionbutton">
                          <%= count%>
                        </button>
                    </div>
                <p><%=note.getTitle()%></p>
                <p style="font-size: 16px;"><%= note.getSender()%></p>
                <div class="versiondate">
                  <p><%= note.getDate()%></p>
                </div></a>
              </div>
              <% count++; 
                } 
            %>
          </div>
        </div>
      </div>
    </div>
    <div class="add-note">
      <button type="button" class="btn btn-warning"><a href="#"><img src="./resources/plus-lg.svg" alt=""></a></button>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
    