<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/header.css" type="text/css">
<title>Header</title>
</head>
<body>
	<div class="container-fluid">
		<div id="header" class="row">
			<img id="headLogo" src="img/logo.gif"></div>
			<form id="logout" method="post" action="login">
				<input type="hidden" name="logout" value="logout" />
				<button type="submit" id="logoutBtn"><i class="glyphicon glyphicon-log-out">Logout</i></button>
			</form>
		</div>
	</div>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
	 		<div class="row">
			    <ul class="nav navbar-nav">
			      <li id="top" class="active col-xs-3 column"><a href="topPage"><i class="glyphicon glyphicon-home"><span class="span">Top</span></i></a></li>
			      <li id="myPage" class="col-xs-3 column"><a href="myPage"><i class="glyphicon glyphicon-user"><span class="span">MyProfile</span></i></a></li>
			      <li id="chat" class="col-xs-3 column"><a href="chat"><i class="glyphicon glyphicon-comment"><span class="span">Chat</span></i></a></li>
			      <li id="follow" class="col-xs-3 column"><a href="getFollowList"><i class="glyphicon glyphicon-star"><span class="span">Follow</span></i></a></li>
			    </ul>
			 </div>
		 </div>
	</nav>
</body>
</html>