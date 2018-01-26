<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/chatSample.css">
<title>Chat Sample</title>
</head>
<body>
    <div class="noscript"><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websockets rely on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></div>
	<div>
	    <p>
	        <input type="text" placeholder="type and press enter to chat" id="chat" />
	    </p>
	    <div id="console-container">
	        <div id="console"></div>
	    </div>
	</div>
    <script src="js/chatSample.js"></script>
</body>
</html>