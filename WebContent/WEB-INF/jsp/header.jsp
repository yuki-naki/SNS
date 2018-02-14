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
</body>
</html>