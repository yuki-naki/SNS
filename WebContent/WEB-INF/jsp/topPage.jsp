<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="tinymce/css/tinymce.css" type="text/css">
<link rel="stylesheet" href="css/topPage.css" type="text/css">
<script src="js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="tinymce/tinymce.min.js"></script>

<title>Top Page</title>
</head>
<body>
	<div class="container-fluid">
		<div id="header" class="row">
			<div class="col-xs-1">
				<form id="logout" method="post" action="login">
					<input type="hidden" name="logout" value="logout" />
					<button type="submit" id="logoutBtn">Logout</button>
				</form>
			</div>
			<div id="headerContent" class="col-xs-10">専門学校東京テクニカルカレッジ</div>
			<div class="col-xs-1"></div>
		</div>
	</div>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
	 		<div class="row">
			    <ul class="nav navbar-nav">
			      <li id="top-left-li" class="active col-xs-3 column"><a href="#">Top</a></li>
			      <li class="col-xs-3 column"><a href="myPage">MyPage</a></li>
			      <li class="col-xs-3 column"><a href="chat">Chat</a></li>
			      <li id="top-right-li" class="col-xs-3 column"><a href="getFollowList">Follow</a></li>
			    </ul>
			 </div>
		 </div>
	</nav>

	 <c:if test="${sessionScope.user.isAdmin}">
		<div class="container textarea-container">
	    	<div class="row">
	    		<div class="col-xs-2"></div>
		        <div class="col-xs-8">
		            <div class="panel panel-info">
		                <div class="panel-body">
		                    <form id="form" method="post" action="notify" enctype="multipart/form-data">
		                        <textarea id="content" name="textarea" class="form-group"></textarea>
		                        <textarea id="content2" name="textarea2" class="hidden"></textarea>
		                        <button id="btn" class="btn btn-primary pull-right" type="submit">投稿</button>
	                    	</form>
		                </div>
		            </div>
		        </div>
		        <div class="col-xs-2"></div>
	    	</div>
		</div>
	</c:if>

	<div class="container">
		<c:forEach var="notification" items="${result}">
	    	<div class="row">
	    		<div class="col-xs-2"></div>
	        	<div class="col-xs-8">
	        		<hr><br>
			        <%-- Date --%>
			        <p>${notification.notificationDate}</p>
			        <%-- Content --%>
			        ${notification.notificationContent}
				</div>
				<div class="col-xs-2"></div>
			</div>
		</c:forEach>
	</div>
	<br>

	<script src="js/topPage.js"></script>
</body>
</html>