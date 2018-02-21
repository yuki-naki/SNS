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
							<a id="headingGroupname" class="heading-name-meta" data-groupId="${result[0]}">${result[1][result[0]].groupName}</a>
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
												<img src="https://bootdey.com/img/Content/avatar/avatar2.png">
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
						<textarea class="form-control" rows="1" id="comment" ${empty result[0] ? "disabled" : ""}></textarea>
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
											<img src="https://bootdey.com/img/Content/avatar/avatar2.png">
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
											<img src="https://bootdey.com/img/Content/avatar/avatar4.png">
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
		<div>
			<div>グループ作成</div>
			<form method='post' action='createGroup'>
				<input type="text" name="groupName">
				<div>フォロー中</div>
				<c:forEach var="followUser" items="${result[3]}">
					<div><input type="checkbox" name="selectedUser" value="${followUser.userId}"/>${followUser.username}</div>
				</c:forEach>
				<input type="submit" value="作成">
   			</form>
  		</div>
	</div>

	<!--addMemberのポップアップ-->
	<div id="addMemberLayer"></div>
	<div id="addMemberPopup">
		<div>
			<div>メンバー追加</div>
			<form method='post' action='addGroupMember'>
				<input type="hidden" name="addMemberGroupId" value="${result[0]}" />
				<c:forEach var="notMember" items="${result[1][result[0]].notMembers}">
					<div><input type="checkbox" name="selectedUser" value="${notMember.userId}"/>${notMember.username}</div>
				</c:forEach>
				<input type="submit" value="追加">
   			</form>
  		</div>
	</div>
	<script src="js/chat.js"></script>
</body>
</html>