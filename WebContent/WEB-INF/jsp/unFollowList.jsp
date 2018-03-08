<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/followList.css">
<link rel="stylesheet" href="css/showUserProfile.css">
<script src="js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/follow.js"></script>
<script src="js/showUserProfile.js"></script>
<title>Follow</title>
</head>
<body>
	<%@include file="header.jsp"%>

	<div class="col-sm-8 col-sm-offset-2 testtest">
		<div class="search">
			<div id="search-bar" style="margin:30px 0px;">
				<div class="col-xs-4">
			      <select class="form-control" name="grade" id="grade">
			      	<option value="default" selected="selected">学年</option>
			        <option value="1年生">1学年</option>
			        <option value="2年生">2学年</option>
			        <option value="3年生">3学年</option>
			        <option value="4年生">4学年</option>
			      </select>
			    </div>
			    <div class="col-xs-4">
			      <select class="form-control" name="department" id="departmentId" >
			      	<option value="default" selected="selected">学科</option>
			        <option value="情報処理科">情報処理科</option>
			        <option value="インテリア科">インテリア科</option>
			        <option value="Web動画クリエーター科">Web動画クリエーター科</option>
			        <option value="環境テクノロジー科">環境テクノロジー科</option>
			        <option value="建築監督科">建築監督科</option>
			        <option value="職員">職員</option>
			      </select>
			    </div>
				<div class="col-xs-4">
				   <div class="input-group">
				     <input type="text" class="form-control" placeholder="Search" name="search" id="test" onclick="change()">
				     <div class="input-group-btn">
				       <button class="btn btn-default" type="submit" style="height:34px;" disabled><i class="glyphicon glyphicon-search"></i></button>
				     </div>
				   </div>
				</div>
			</div>
		</div>
		<div class="memberList">
			<table border="0" class="table table-condensed" id="table">
				<thead>
					<tr>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				 </thead>
				 <tbody>
					<c:forEach var="user" items="${result}">
						<form method='post' action='follow'>
							<input type="hidden" name="userId" value="${user.userId}"	/>
							<tr>
								<td class="col-xs-2 text-center" width="100" ><img src="loadIcon?userId=${user.userId}" id="${user.userId}" class="img-circle modal-open icon" alt="icon"></td><!-- 0番目 -->
								<td class="col-xs-4 text-left">${user.username}</td><!-- １番目 -->
								<td class="col-xs-2 text-left"><c:if test="${user.departmentName != '職員'}">${user.admissionYear}年生</c:if><!-- ２番目 -->
								<td class="col-xs-3 text-left">${user.departmentName}</td><!-- ３番目 -->
								<td class="col-xs-1"><input type='submit' class="btn btn-info " value='follow'></td><!-- ４番目 -->
							</tr>
						</form>
						<div id="modal${user.userId}" class="modal-content" >
							<div class="modal-content-innar">
								<div class="col">
									<h1>${user.username}</h1>
								</div>
								<div class="col-sm-3 col-sm-offset-1 content-center" id="iconwrap">
									<img name="icon" class="img-circle profileIcon" src="loadIcon?userId=${user.userId}">
								</div>
								<div class="col-sm-5 col-sm-offset-2">
									<h3><c:if test="${user.departmentName != '職員'}">${user.admissionYear}年生</c:if></h3>
									<h3>学科:${user.departmentName}</h3>
								</div>
								<div class="col-sm-12">
									<h3>自己紹介文</h3>
									<textarea name="comment" class="form-control" rows="8" id="comment" readonly>${user.userIntroduction}</textarea>
								</div>
							</div>
						</div>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="actionButton">
			<a href="getFollowList"><button type="button" class="btn btn-success">FollowList</button></a>
    	</div>
	</div>
</body>
</html>