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
<script>
	$(function(){
		$("#config").click(function(){
			$('#comment').attr('readonly',false);
			$('#config').attr("disabled",true);
			$('#save').attr("disabled",false);
			$('#icon').css('cursor','pointer');
			$('#upload').attr("disabled",false);

			$('#icon').css('opacity',0.5);

		});
	})

	$(function(){
		$('#icon').click(function(){
			document.getElementById("upload").click();
		});
	})
</script>
<title>GroupEdit</title>
</head>
<body>
	<%@include file="header.jsp" %>

	<div class="container-fluid text-center">
		<div class="row justify-content-center">
			<div class="col-sm-8 col-sm-offset-2" id="profile">
				<form method="post" action="GroupIconUploadServlet" enctype="multipart/form-data">
				<c:forEach var="result" items="${result}">
						<div class="col">

							<h1>GroupEdit</h1>
						</div>
						<div class="col-sm-3 col-sm-offset-1 content-center" id="iconwrap">
							<input type="hidden" name="groupId" value="${result.groupId}"></input>
							<img name="icon" class="img-circle" src="loadGroupIcon?groupId=${result.groupId}" id="icon">
						</div>
						<div class="col-sm-5 col-sm-offset-2">
							<h3>GroupName<br><input type="text" name="groupName" value="${result.groupName}"></input></h3>
						</div>
						<div class="col-sm-12 text-right">
							<input type="file" id="upload" name="iconimg" disabled="true">
							<button type="button" class="btn btn-success btn-md" id="config" >編集</button>
							<button type="submit" id="save" class="btn btn-success btn-md" disabled="true">保存</button>

						</div>
					</c:forEach>
				</form>
			</div>
		</div>
	</div>
	<script src="js/myPage.js"></script>
</body>
</html>