<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/topPage.css">
<link rel="stylesheet" href="css/myPage.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
	$(function(){
		$("#config").click(function(){
			$('#comment').attr('readonly',false);
			$('#config').attr("disabled",true);

		});
	});
</script>
<title>MyProfile</title>
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
		    <ul class="nav navbar-nav">
		      <li id="top-li"><a href="login">Top</a></li>
		      <li class="active"><a href="myPage">MyPage</a></li>
		      <li><a href="chatRoom">Chat</a></li>
		      <li><a href="followList">Follow</a></li>
		    </ul>
	</nav>
	<div class="container-fluid text-center">
		<div class="row justify-content-center">
			<div class="col-sm-8 col-sm-offset-2" id="profile">
				<form method="post" action="IconTestServlet" enctype="multipart/form-data">
						<div class="col">
							<h1>MyProfile</h1>
						</div>
						<div class="col-sm-3 col-sm-offset-1 content-center" id="iconwrap">
							<img name="icon" class="img-circle" src="https://bootdey.com/img/Content/avatar/avatar1.png" id="icon">
						</div>
						<div class="col-sm-5 col-sm-offset-2">
						<c:forEach var="result" items="${result}">
							<h3>名前:${result.userName }</h3>
							<h3>学年:${result.schoolYear }年</h3>
							<h3>学科:${result.departmentName }</h3>
						</div>
						<div class="col-sm-12">
							<h3>自己紹介文</h3>
								<textarea name="comment" class="form-control" rows="8" id="comment" readonly>${result.userIntroduction}</textarea>
						</div>
						<div class="col-sm-12 text-right">
							<input type="file" id="upload" name="iconimg">
							<button type="button" class="btn btn-success btn-md" id="config" >編集</button>
							<a href="myPageSetup"><button type="submit" class="btn btn-success btn-md" >保存</button></a>

						</div>
						</c:forEach>

				</form>
			</div>
		</div>
	</div>
</body>
</html>