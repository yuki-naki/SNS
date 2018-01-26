<%@ page language="java"  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <html>
    <head>
    	<title>chat page</title>
    	<meta charset="utf-8">
    	<meta charset="viewport" content="width=device, initial-scale=1">
    	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    	<link rel="stylesheet" href="css/index.css">

    	<link rel="stylesheet" href="css/topPage.css">

    	<link rel="stylesheet" href="css/chat.css">

    	<script src="js/vendor/jquery.min.js"></script>
    	<script src="js/vendor/bootstrap.min.js"></script>
    </head>
    <body>
    <div class="container-fluid">
    <div id="header" class="row">
			<div class="col-lg-4"></div>
			<div id="headerContent" class="col-lg-4">専門学校東京テクニカルカレッジ</div>
			<div class="col-lg-4"></div>
		</div>
    </div>
    <nav class="navbar navbar-default">
  		<div class="container-fluid">
		    <ul class="nav navbar-nav">
		      <li id="top-li" class="active"><a href="#">Top</a></li>
		      <li><a href="#">MyPage</a></li>
		      <li><a href="#">Chat</a></li>
		      <li><a href="#">Follow</a></li>
		    </ul>
	  </div>
	</nav>

    <table border="1" class="table table-condensed table-hover">

		<c:forEach var="chatcontent" items="${content} ">
		<tr>
		<td class="col-lg-1"><img src="img/image.jpg" id="icon" class="img-circle" alt="anoni"><br>AL ABAYAJI ALEXANDRE</td>

		<td class="col-lg-11" id="chatcon">[chatContent]</td>
		</tr>
		</c:forEach>
    </table>
    </body>
    </html>