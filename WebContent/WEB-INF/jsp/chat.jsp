<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="tinymce/css/tinymce.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/chat.css">
<link rel="stylesheet" href="css/topPage.css">
<script src="js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

<title>Chat</title>
</head>
<body>
	<div class="container-fluid">
		<div id="header" class="row">
			<div class="col-xs-1">
				<form id="logout" method="post" action="login">
					<input type="hidden" name="logout" value="logout" />
					<button type="submit" id="logoutBtn">Logout</button>
				</form>
			</div>
			<div id="headerContent" class="col-xs-10">専門学校東京テクニカルカレッジ</div>
			<div class="col-xs-1"></div>
		</div>
	</div>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
	 		<div class="row">
			    <ul class="nav navbar-nav">
			      <li id="top-left-li" class="col-xs-3 column"><a href="login">Top</a></li>
			      <li class="col-xs-3 column"><a href="#">MyPage</a></li>
			      <li class="active col-xs-3 column"><a href="chatRoom">Chat</a></li>
			      <li id="top-right-li" class="col-xs-3 column"><a href="#">Follow</a></li>
			    </ul>
			 </div>
		 </div>
	</nav>



	<div class="container app" >
    <div class="row app-one" >

    <div class="col-sm-8 conversation" >
      <div class="row heading" >
		<div class="col-sm-2 col-xs-2 heading-compose  pull-right">
		   <i class="fa fa-comments fa-2x  pull-right" aria-hidden="true"></i>
         </div>
        <div class="col-xs-8 heading-name" >
          <a id="groupname" class="heading-name-meta">
          	<c:if test="${not empty result[0]}">
          		${result[1][result[0]].groupName}
          	</c:if>
          </a>
          <span class="heading-online">Online</span>
        </div>
      </div>

      <div class="row message" id="conversation" >

		<c:if test="${not empty result[0]}">
			<c:forEach var="message" items="${result[1][result[0]].messages}">
				<c:choose>
					<c:when test="${sessionScope.user.userId eq message.user.userId}">
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
						<div class="row message-body" >
							<div class="col-xs-1 messageBody-avatar" >
								 <div class="avatar-icon" >
									<img src="https://bootdey.com/img/Content/avatar/avatar2.png">
								 </div>
							</div>
				          <div class="col-xs-11 message-main-receiver" >
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

      <div class="row reply" >
        <div class="col-sm-1 col-xs-1 reply-uploading">
          <i class="glyphicon glyphicon-picture" aria-hidden="true"></i>
        </div>
        <div class="col-sm-9 col-xs-9 reply-main">
          <textarea class="form-control" rows="1" id="comment"></textarea>
        </div>
        <div class="col-sm-1 col-xs-1 reply-send">
          <i id="reply_btn" class="fa fa-send fa-2x" aria-hidden="true"></i>
        </div>
      </div>
    </div>
        <div class="col-sm-4 side" >
      <div class="side-one" >
        <div class="row heading" >
          <div class="col-sm-3 col-xs-3 heading-avatar" >
            <div class="heading-avatar-icon" >
              <img src="https://bootdey.com/img/Content/avatar/avatar1.png">
            </div>
          </div>
          <span class="grouplist_header">Group List</span>
        </div>

        <div class="row searchBox" >
          <div class="col-sm-12 searchBox-inner" >
            <div class="form-group has-feedback" >
              <input id="searchText" type="text" class="form-control" name="searchText" placeholder="Search">
              <span class="glyphicon glyphicon-search form-control-feedback"></span>
            </div>
          </div>
        </div>

		<c:forEach var="chat" items="${result[1]}">
		<form id="form" method="post" action="chat">
		<input type="hidden" name="sessionUserId" value="${sessionScope.user.userId}" />
		<input type="hidden" name="chatId" value="${chat.key}" />
          <button type="submit" class="row sideBar-body">
            <div class="col-xs-1 sideBar-avatar" >
              <div class="avatar-icon" >
                <img src="https://bootdey.com/img/Content/avatar/avatar2.png">
              </div>
            </div>
            <div class="col-xs-11 sideBar-main" >
              <div class="row" >
                <div class="col-xs-8 sideBar-name" >
                  <span class="name-meta">${chat.value.groupName}
                </span>
                </div>
                <div class="col-xs-4 pull-right sideBar-time" >
                  <span class="time-meta pull-right">${chat.value.messages[fn:length(chat.value.messages)-1].date}
                </span>
                </div>
              </div>
            </div>
          </button>
          </form>
		</c:forEach>

        </div>
      </div>

      <div class="side-two" >
        <div class="row newMessage-heading" >
          <div class="row newMessage-main" >
            <div class="col-sm-2 col-xs-2 newMessage-back" >
              <i class="fa fa-arrow-left" aria-hidden="true"></i>
            </div>
            <div class="col-sm-10 col-xs-10 newMessage-title" >
              New Chat
            </div>
          </div>
        </div>

        <!--<div class="row composeBox" >
          <div class="col-sm-12 composeBox-inner" >
            <!--<div class="form-group has-feedback" >
              <input id="composeText" type="text" class="form-control" name="searchText" placeholder="Search People">
              <span class="glyphicon glyphicon-search form-control-feedback"></span>
            </div>-->
          </div>
        </div>-->

        <div class="row compose-sideBar" >
          <div class="row sideBar-body" >
            <div class="col-sm-3 col-xs-3 sideBar-avatar" >
              <div class="avatar-icon" >
                <img src="https://bootdey.com/img/Content/avatar/avatar1.png">
              </div>
            </div>
            <div class="col-sm-9 col-xs-9 sideBar-main" >
              <div class="row" >
                <div class="col-sm-8 col-xs-8 sideBar-name" >
                  <span class="name-meta">John Doe
                </span>
                </div>
                <div class="col-sm-4 col-xs-4 pull-right sideBar-time" >
                  <span class="time-meta pull-right">18:18
                </span>
                </div>
              </div>
            </div>
          </div>

          <div class="row sideBar-body" >
            <div class="col-sm-3 col-xs-3 sideBar-avatar" >
              <div class="avatar-icon" >
                <img src="https://bootdey.com/img/Content/avatar/avatar2.png">
              </div>
            </div>
            <div class="col-sm-9 col-xs-9 sideBar-main" >
              <div class="row" >
                <div class="col-sm-8 col-xs-8 sideBar-name" >
                  <span class="name-meta">John Doe
                </span>
                </div>
                <div class="col-sm-4 col-xs-4 pull-right sideBar-time" >
                  <span class="time-meta pull-right">18:18
                </span>
                </div>
              </div>
            </div>
          </div>
          <div class="row sideBar-body" >
            <div class="col-sm-3 col-xs-3 sideBar-avatar" >
              <div class="avatar-icon" >
                <img src="https://bootdey.com/img/Content/avatar/avatar3.png">
              </div>
            </div>
            <div class="col-sm-9 col-xs-9 sideBar-main" >
              <div class="row" >
                <div class="col-sm-8 col-xs-8 sideBar-name" >
                  <span class="name-meta">John Doe
                </span>
                </div>
                <div class="col-sm-4 col-xs-4 pull-right sideBar-time" >
                  <span class="time-meta pull-right">18:18
                </span>
                </div>
              </div>
            </div>
          </div>
          <div class="row sideBar-body" >
            <div class="col-sm-3 col-xs-3 sideBar-avatar" >
              <div class="avatar-icon" >
                <img src="https://bootdey.com/img/Content/avatar/avatar4.png">
              </div>
            </div>
            <div class="col-sm-9 col-xs-9 sideBar-main" >
              <div class="row" >
                <div class="col-sm-8 col-xs-8 sideBar-name" >
                  <span class="name-meta">John Doe
                </span>
                </div>
                <div class="col-sm-4 col-xs-4 pull-right sideBar-time" >
                  <span class="time-meta pull-right">18:18
                </span>
                </div>
              </div>
            </div>
          </div>
          <div class="row sideBar-body" >
            <div class="col-sm-3 col-xs-3 sideBar-avatar" >
              <div class="avatar-icon" >
                <img src="https://bootdey.com/img/Content/avatar/avatar5.png">
              </div>
            </div>
            <div class="col-sm-9 col-xs-9 sideBar-main" >
              <div class="row" >
                <div class="col-sm-8 col-xs-8 sideBar-name" >
                  <span class="name-meta">John Doe
                </span>
                </div>
                <div class="col-sm-4 col-xs-4 pull-right sideBar-time" >
                  <span class="time-meta pull-right">18:18
                </span>
                </div>
              </div>
            </div>
          </div>
          <div class="row sideBar-body" >
            <div class="col-sm-3 col-xs-3 sideBar-avatar" >
              <div class="avatar-icon" >
                <img src="https://bootdey.com/img/Content/avatar/avatar6.png">
              </div>
            </div>
            <div class="col-sm-9 col-xs-9 sideBar-main" >
              <div class="row" >
                <div class="col-sm-8 col-xs-8 sideBar-name" >
                  <span class="name-meta">John Doe
                </span>
                </div>
                <div class="col-sm-4 col-xs-4 pull-right sideBar-time" >
                  <span class="time-meta pull-right">18:18
                </span>
                </div>
              </div>
            </div>
          </div>
          <div class="row sideBar-body" >
            <div class="col-sm-3 col-xs-3 sideBar-avatar" >
              <div class="avatar-icon" >
                <img src="https://bootdey.com/img/Content/avatar/avatar2.png">
              </div>
            </div>
            <div class="col-sm-9 col-xs-9 sideBar-main" >
              <div class="row" >
                <div class="col-sm-8 col-xs-8 sideBar-name" >
                  <span class="name-meta">John Doe
                </span>
                </div>
                <div class="col-sm-4 col-xs-4 pull-right sideBar-time" >
                  <span class="time-meta pull-right">18:18
                </span>
                </div>
              </div>
            </div>
          </div>
          <div class="row sideBar-body" >
            <div class="col-sm-3 col-xs-3 sideBar-avatar" >
              <div class="avatar-icon" >
                <img src="https://bootdey.com/img/Content/avatar/avatar3.png">
              </div>
            </div>
            <div class="col-sm-9 col-xs-9 sideBar-main" >
              <div class="row" >
                <div class="col-sm-8 col-xs-8 sideBar-name" >
                  <span class="name-meta">John Doe
                </span>
                </div>
                <div class="col-sm-4 col-xs-4 pull-right sideBar-time" >
                  <span class="time-meta pull-right">18:18
                </span>
                </div>
              </div>
            </div>
          </div>
          <div class="row sideBar-body" >
            <div class="col-sm-3 col-xs-3 sideBar-avatar" >
              <div class="avatar-icon" >
                <img src="https://bootdey.com/img/Content/avatar/avatar4.png">
              </div>
            </div>
            <div class="col-sm-9 col-xs-9 sideBar-main" >
              <div class="row" >
                <div class="col-sm-8 col-xs-8 sideBar-name" >
                  <span class="name-meta">John Doe
                </span>
                </div>
                <div class="col-sm-4 col-xs-4 pull-right sideBar-time" >
                  <span class="time-meta pull-right">18:18
                </span>
                </div>
              </div>
            </div>
          </div>
          <div class="row sideBar-body" >
            <div class="col-sm-3 col-xs-3 sideBar-avatar" >
              <div class="avatar-icon" >
                <img src="https://bootdey.com/img/Content/avatar/avatar5.png">
              </div>
            </div>
            <div class="col-sm-9 col-xs-9 sideBar-main" >
              <div class="row" >
                <div class="col-sm-8 col-xs-8 sideBar-name" >
                  <span class="name-meta">John Doe
                </span>
                </div>
                <div class="col-sm-4 col-xs-4 pull-right sideBar-time" >
                  <span class="time-meta pull-right">18:18
                </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="js/chat.js"></script>
</body>
</html>