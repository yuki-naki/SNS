<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="css/index.css" rel="stylesheet" type="text/css">
	<script src="js/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<title>Login</title>
</head>
<body>
   <div class="container-fluid">
		<div id="header" class="row">
			<div class="col-xs-1 column"></div>
			<div id="headerContent" class="col-xs-10 column">専門学校東京テクニカルカレッジ</div>
			<div class="col-xs-1 column"></div>
		</div>
		<c:if test="${requestScope.sessionTimeout}">
			<div class="alert alert-warning">
				<strong>Session Timeout</strong>
			</div>
		</c:if>
		<div class="row">
			<div class="col-xs-1 column"></div>

			<div id="loginBox" class="col-xs-10 column">

				<div class="panel panel-info">

					<div class="panel-heading">
						<div class="panel-title">Login</div>
					</div>

					<div id="panelBody" class="panel-body" >

						<form class="form-horizontal" method="post" action="login">

							<c:if test="${requestScope.notChecked}">
								<div>
									<p style="color:red; margin-top:-10px;">Wrong id or password</p>
								</div>
							</c:if>

							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
								<input id="username" type="text" class="form-control" name="id" placeholder="Id" value="${requestScope.paramId}">
							</div>

							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
								<input id="password" type="password" class="form-control" name="password" placeholder="Password">
							</div>

							<div class="form-group">
								<div class="col-xs-12">
								  <button class="btn btn-success">Login</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-xs-1 column"></div>
		</div>
    </div>
</body>
</html>