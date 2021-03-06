<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/switcher.css">
<link rel="stylesheet" href="css/topPage.css">
<link rel="stylesheet" href="css/followList.css">
<script src="js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="flat-ui/js/flat-ui.min.js"></script>
<script src="flat-ui/js/application.js"></script>
<title>Follow List</title>
</head>
<body>
	<%@include file="header.jsp" %>

	<div class="container-fluid text-center">
		<div class="row justify-content-center">
			<div class="col-sm-8 col-sm-offset-2">
				<div id="search-bar" style="margin:30px 0px;">
					<div class="col-xs-4">
				      <select class="form-control" name="grade">
				      	<option value="default" selected="selected">学年</option>
				        <option value="1">1学年</option>
				        <option value="2">2学年</option>
				        <option value="3">3学年</option>
				        <option value="4">4学年</option>
				      </select>
				    </div>
				    <div class="col-xs-4">
				      <select class="form-control" name="department">
				      	<option value="default" selected="selected">学科</option>
				        <option value="a">情報処理科</option>
				        <option value="b">建築科</option>
				        <option value="c">バイオ科</option>
				      </select>
				    </div>
				    <div class="col-xs-4">
						<form action="/action_page.php">
						   <div class="input-group">
						     <input type="text" class="form-control" placeholder="Search" name="search">
						     <div class="input-group-btn">
						       <button class="btn btn-default" type="submit" style="height:34px;"><i class="glyphicon glyphicon-search"></i></button>
						     </div>
						   </div>
						</form>
					</div>
				<div>
					<form method='post' action='createGroup'>
						<input type="text" name="groupName">
						<div>フォロー中</div>
						<c:forEach var="followUser" items="${result[0]}">
							<div><input type="checkbox" name="selectedUser" value="${followUser.userId}"/>${followUser.username}</div>
						</c:forEach>
						<div>フォローしてない</div>
						<c:forEach var="unFollowUser" items="${result[1]}">
							<div><input type="checkbox" name="selectedUser" value="${unFollowUser.userId}"/>${unFollowUser.username}</div>
						</c:forEach>
						<input type="submit" value="作成">
	    			</form>
    			</div>
		</div>
    </div>
</body>
</html>