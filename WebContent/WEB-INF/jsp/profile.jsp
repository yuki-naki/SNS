<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/myPage.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<title>Profile</title>
</head>
<body>

	<div class="container-fluid text-center">
		<div class="row justify-content-center">
			<div class="col-sm-8 col-sm-offset-2" id="profile">
				<form method="post" action="uploadIcon" enctype="multipart/form-data">
				<c:forEach var="result" items="${result}">
						<div class="col">

							<h1>Profile</h1>
						</div>
						<div class="col-sm-3 col-sm-offset-1 content-center" id="iconwrap">
							<img name="icon" class="img-circle" src="loadIcon?userId=${result.userId}" id="icon">
						</div>
						<div class="col-sm-5 col-sm-offset-2">

							<h3>名前:${result.username }</h3>
							<h3>学年:${result.admissionYear}年</h3>
							<h3>学科:${result.departmentName }</h3>
						</div>
						<div class="col-sm-12">
							<h3>自己紹介文</h3>
								<textarea name="comment" class="form-control" rows="8" id="comment" readonly>${result.userIntroduction}</textarea>
						</div>
						<div class="col-sm-12 text-right">
						</div>
					</c:forEach>
				</form>
			</div>
		</div>
	</div>
	<script src="js/myPage.js"></script>
</body>
</html>