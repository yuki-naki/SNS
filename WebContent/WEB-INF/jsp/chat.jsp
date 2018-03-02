<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="tinymce/css/tinymce.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/chat.css">
<script src="js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/popup.js"></script>
<script src="js/search.js"></script>

<title>Chat</title>
</head>
<body>
	<%@include file="header.jsp" %>

	<div class="container app">

		<div class="row app-one">
			<div class="col-sm-8 conversation">
				<div class="row conversationHeading">
					<div class="col-xs-12 heading-name">
						<c:if test="${not empty result[0]}">
							<form method="post" action="groupEdit" name="form1">
							<input type="hidden" name="groupId" value="${result[0]}"></input>
							<a id="headingGroupname"  href="javascript:form1.submit()" class="heading-name-meta" data-groupId="${result[0]}">${result[1][result[0]].groupName}</a>
							</form>

						</c:if>
					</div>
				</div>

				<div class="row ${empty result[0] ? 'message' : 'message-group'}" id="conversation">

					<c:if test="${not empty result[0]}">
						<c:forEach var="message" items="${result[1][result[0]].messages}">
							<c:choose>
								<c:when test="${sessionScope.user.userId eq message.userId}">
									<div class="row message-body">
										<div class="col-xs-12 message-main-sender">
											<div class="sender">
												<div class="message-text">${message.content}</div>
												<span class="message-time pull-right">${message.date}</span>
											</div>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="row message-body">
										<div class="col-xs-1 messageBody-avatar">
											<div class="avatar-icon">
												<img src="loadIcon?userId=${message.user.userId}">
											</div>
										</div>
										<div class="col-xs-11 message-main-receiver">
											<div class="row receiver-name">${message.user.username}</div>
											<div class="receiver">
												<div class="message-text">${message.content}</div>
												<span class="message-time pull-right">${message.date}</span>
											</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</div>

				<div id="reply" class="row reply" data-user='${result[2]}'>
					<div class="col-xs-1 reply-uploading">
						<i class="glyphicon glyphicon-picture" aria-hidden="true"></i>
					</div>
					<div class="col-xs-10 reply-main">
						<textarea class="form-control" id="comment" ${empty result[0] ? "disabled" : ""}></textarea>
					</div>
					<div class="col-xs-1 reply-send">
						<i id="reply_btn" class="fa fa-send fa-2x" aria-hidden="true"></i>
					</div>
				</div>
			</div>

			<div class="col-sm-4 side">

				<div class="side-one">
					<div class="row heading">
						<div class="col-xs-1 heading-compose">
							<i id="membersListBtn" class="fa fa-comments fa-2x ${empty result[0] ? 'hidden' : ''}" aria-hidden="true"></i>
						</div>
						<span class="col-xs-11 grouplist_header">Groups List</span>
					</div>

					<div class="row searchBox">
						<div class="col-xs-12 searchBox-inner">
							<div class="form-group has-feedback">
								<input id="searchGroupTxtBox" type="text" class="form-control" placeholder="Search Group">
								<span id ="searchGroupBtn" class="glyphicon glyphicon-search form-control-feedback"></span>
							</div>
						</div>
					</div>

					<div class="row compose-sideBar">
						<c:forEach var="chat" items="${result[1]}">
							<form method="post" action="chat">
								<input type="hidden" name="chatId" value="${chat.key}" />
								<button type="submit" class="row sideBar-body group">
									<div class="col-xs-1 sideBar-avatar">
										<div class="avatar-icon">
											<img src="loadGroupIcon?groupId=${chat.key}">
										</div>
									</div>
									<div class="col-xs-11 sideBar-main">
										<div class="row">
											<div id="sideGroupname" data-groupId="${chat.key}"
												class="col-xs-8 sideBar-name">
												<span class="name-meta">${chat.value.groupName}</span>
											</div>
											<div id="sideDate" class="col-xs-4 pull-right sideBar-time">
												<span class="time-meta pull-right">${chat.value.messages[fn:length(chat.value.messages)-1].date}</span>
											</div>
										</div>
									</div>
								</button>
								<hr>
							</form>
						</c:forEach>
					</div>

					<div class="row sideBottom">
						<input id="groupIcon" class="iconImage" type="image" src="img/group.png" />
					</div>
				</div>

				<div class="side-two">
					<div class="row newMessage-heading">
						<div class="row newMessage-main">
							<div class="col-xs-1 newMessage-back">
								<i id="backGroupBtn" class="fa fa-arrow-right" aria-hidden="true"></i>
							</div>
							<div class="col-xs-11 newMessage-title">Members List</div>
						</div>
					</div>

					<div class="row composeBox">
						<div class="col-sm-12 composeBox-inner">
							<div class="form-group has-feedback">
								<input id="searchMemberTxtBox" type="text" class="form-control" name="searchText" placeholder="Search People">
								<span id="searchMembersBtn" class="glyphicon glyphicon-search form-control-feedback"></span>
							</div>
						</div>
					</div>

					<div class="row compose-sideBar">
						<c:if test="${not empty result[0]}">
							<c:forEach var="member" items="${result[1][result[0]].members}">
								<div class="row sideBar-body member">
									<div class="col-xs-1 sideBar-avatar">
										<div class="avatar-icon">
											<img src="loadIcon?userId=${member.userId}">
										</div>
									</div>
									<div class="col-xs-11 sideBar-main">
										<div class="row">
											<div class="col-xs-8 sideBar-name">
												<span class="name-meta">${member.username}</span>
											</div>
											<div class="col-xs-4 pull-right sideBar-time">
												<span class="time-meta pull-right"></span>
											</div>
										</div>
									</div>
								</div>
								<hr>
							</c:forEach>
						</c:if>
					</div>

					<div class="row sideBottom">
						<input id="addMember"class="iconImage" type="image" src="img/user.png" />
					</div>

				</div>
			</div>
		</div>
	</div>
	<!--createGroupのポップアップ-->
	<div id="createGroupLayer"></div>
	<div id="createGroupPopup">
		<div class="popupRemoveButton">
			<button id="closeCreateGroup"><i class=" glyphicon glyphicon-remove"></i></button>
		</div>
		<div class="popupHeader">グループ作成</div>

		<form class="popupForm" method='post' action='createGroup'>
			<div class="popupGroupName">
				<input type="text" placeholder="グループ名を入力してください。" class="form-control" name="groupName">
			</div>
			<div class="popupSearch">
				<div id="search-bar"">
					<div class="col-xs-4">
				      <select class="form-control" name="grade" id="grade1">
				      	<option value="default" selected="selected">学年</option>
				        <option value="1年生">1学年</option>
				        <option value="2年生">2学年</option>
				        <option value="3年生">3学年</option>
				        <option value="4年生">4学年</option>
				      </select>
				    </div>
				    <div class="col-xs-4">
				      <select class="form-control" name="department" id="departmentId1" >
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
					     <input type="text" class="form-control" placeholder="Search" name="search" id="test1" onclick="change()">
					     <div class="input-group-btn">
							<button class="btn btn-default" type="submit" style="height:34px;" disabled><i class="glyphicon glyphicon-search"></i></button>
					     </div>
					   </div>
					</div>
				</div>
			</div>
			<div class="popupMemberList">
				<table border="0" class="table table-condensed" id="table1">
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
							<c:forEach var="followUser" items="${result[3]}">
								<tr>
									<td class="col-xs-1"><input type="checkbox" name="selectedUser" value="${followUser.userId}"/></td>
									<td class="col-xs-1" width="100" ><img src="loadIcon?userId=${followUser.userId}" id="icon" class="img-circle" alt="icon"></td><!-- 1番目 -->
									<td class="col-xs-5 text-left">${followUser.username}</td><!-- 2番目 -->
									<td class="col-xs-2 text-left"><c:if test="${followUser.departmentName != '職員'}">${followUser.admissionYear}年生</c:if><!-- 3番目 -->
									<td class="col-xs-3 text-left">${followUser.departmentName}</td><!-- 4番目 -->
								</tr>
							</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="popupActionButton">
				<input type="submit" class="btn btn-success" value="作成">
			</div>
		</form>
	</div>

	<!--addMemberのポップアップ-->
	<div id="addMemberLayer"></div>
	<div id="addMemberPopup">
		<div class="popupRemoveButton">
			<button id="closeAddMember"><i class=" glyphicon glyphicon-remove"></i></button>
		</div>
		<div class="popupHeader">メンバー追加</div>

		<form class="popupForm" method='post' action='addGroupMember'>
			<input type="hidden" name="addMemberGroupId" value="${result[0]}">
			<div class="popupGroupName">
				${result[1][result[0]].groupName}
			</div>
			<div class="popupSearch">
				<div id="search-bar"">
					<div class="col-xs-4">
				      <select class="form-control" name="grade" id="grade2">
				      	<option value="default" selected="selected">学年</option>
				        <option value="1年生">1学年</option>
				        <option value="2年生">2学年</option>
				        <option value="3年生">3学年</option>
				        <option value="4年生">4学年</option>
				      </select>
				    </div>
				    <div class="col-xs-4">
				      <select class="form-control" name="department" id="departmentId2" >
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
					     <input type="text" class="form-control" placeholder="Search" name="search" id="test2" onclick="change()">
					     <div class="input-group-btn">
							<button class="btn btn-default" type="submit" style="height:34px;" disabled><i class="glyphicon glyphicon-search"></i></button>
					     </div>
					   </div>
					</div>
				</div>
			</div>
			<div class="popupMemberList">
				<table border="0" class="table table-condensed" id="table2">
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
							<c:forEach var="notMember" items="${result[1][result[0]].notMembers}">
								<tr>
									<td class="col-xs-1"><input type="checkbox" name="selectedUser" value="${notMember.userId}"/></td>
									<td class="col-xs-1" width="100" ><img src="loadIcon?userId=${notMember.userId}" id="icon" class="img-circle" alt="icon"></td><!-- 1番目 -->
									<td class="col-xs-5 text-left">${notMember.username}</td><!-- 2番目 -->
									<td class="col-xs-2 text-left"><c:if test="${notMember.departmentName != '職員'}">${notMember.admissionYear}年生</c:if><!-- 3番目 -->
									<td class="col-xs-3 text-left">${notMember.departmentName}</td><!-- 4番目 -->
								</tr>
							</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="popupActionButton">
				<input type="submit" class="btn btn-success" value="追加">
			</div>
		</form>
	</div>
	<script src="js/chat.js"></script>
</body>
</html>