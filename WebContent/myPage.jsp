<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="css/topPage.css">
<link rel="stylesheet" href="css/myPage.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
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
		      <li id="top-li"><a href="#">Top</a></li>
		      <li class="active"><a href="#">MyPage</a></li>
		      <li><a href="#">Chat</a></li>
		      <li><a href="#">Follow</a></li>
		    </ul>
	</nav>
	<div class="container-fluid text-center">
		<div class="row justify-content-center">
			<div class="col-sm-6 col-sm-offset-3" id="profile">
				<form method="post" action="*******">

						<div class="col">
							<h1>MyProfile</h1>
						</div>


						<div class="col-sm-3 col-sm-offset-1 content-center">
							<img class="img-circle" src="img/man.png" id="icon">
						</div>
						<div class="col-sm-5 col-sm-offset-2">
							<h3>名前:#####</h3>
							<h3>学年:##年</h3>
							<h3>学科:#######科</h3>
						</div>


						<div class="col-sm-12">
							<h3>自己紹介文</h3>
								<textarea class="form-control" rows="8" id="comment"></textarea>
						</div>


						<div class="col-sm-12 text-right">
							<input type="submit" class="btn btn-success btn-md" value="保存">
						</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>