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
				<div>
					<table border="0" class="table table-condensed" >
						<c:forEach var="group" items="${result}">
							<form method='post' action='deleteGroup'>
								<input type="hidden" name="deleteTargetGroupId" value="${group.groupId}"	/>
								<tr><td width="100" ><img src="img/image.jpg" id="icon" class="img-circle" alt="anoni"></td><td class="text-left">${group.groupName}</td><td width="100"><input type='submit' class="btn btn-danger " value='削除'></td></tr>
							</form>
						</c:forEach>
					</table>
				</div>
    		</div>
		</div>
    </div>
</body>
</html>