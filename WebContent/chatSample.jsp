<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Chat Sample</title>
</head>
<body>
    <form>
        <input type="text" id="messageinput"/>
        <button type="button" onclick="send();" >Send</button>
    </form>
    <br>
    <textarea id="textarea" rows="10" cols="50"></textarea>
    <script src="js/chatSample.js"></script>
</body>
</html>