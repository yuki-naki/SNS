<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="flat-ui/css/flat-ui.min.css">
<link rel="stylesheet" href="css/topPage.css">
<link rel="stylesheet" href="css/followList.css">
<script src="js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="flat-ui/js/flat-ui.min.js"></script>
<script src="flat-ui/js/application.js"></script>
<title>Follow List</title>
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

	<div class="container">
		<div class="col-sm-6 col-sm-offset-3 text-left">
			<form action="/action_page.php">
			   <div class="input-group">
			     <input type="text" class="form-control" placeholder="Search" name="search">
			     <div class="input-group-btn">
			       <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
			     </div>
			   </div>
			</form>
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
	    </div>
		<table border="1" class="table table-condensed table-hover">

			<c:forEach var="chatcontent" items="${content} ">
			<tr>
			<td class="col-lg-1"><img src="img/image.jpg" id="icon" class="img-circle" alt="anoni">AL ABAYAJI ALEXANDRE<input type="checkbox" checked data-toggle="switch" name="default-switch"></td>
			</tr>
			</c:forEach>
	    </table>
    </div>
</body>
</html>