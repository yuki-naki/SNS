<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="tinymce/css/tinymce.css">
<link rel="stylesheet" href="css/topPage.css">
<script src="js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="tinymce/tinymce.min.js"></script>

<title>Top Page</title>
</head>
<body>
	<div class="container-fluid">
		<div id="header" class="row">
			<div class="col-lg-4"></div>
			<div id="headerContent" class="col-lg-4">専門学校東京テクニカルカレッジ</div>
			<div class="col-lg-4"></div>
		</div>
	</div>
	<nav class="navbar navbar-default">
  		<div class="container-fluid">
		    <ul class="nav navbar-nav">
		      <li id="top-li" class="active"><a href="#">Top</a></li>
		      <li><a href="#">MyPage</a></li>
		      <li><a href="#">Chat</a></li>
		      <li><a href="#">Follow</a></li>
		    </ul>
	  </div>
	</nav>


	<div class="container textarea-container">
    	<div class="row">
    		<div class="col-lg-2"></div>
	        <div class="col-lg-8">
	            <div class="panel panel-info">
	                <div class="panel-body">
	                    <form id="form" method="post" action="AddServlet" enctype="multipart/form-data">
	                        <textarea id="content" name="textarea" class="form-group"></textarea>
	                        <textarea id="content2" name="textarea2" class="hidden"></textarea>
	                        <%-- <input id="upload" type="file" name="file" class="" />--%>
	                        <button id="btn" class="btn btn-primary pull-right" type="submit">Submit</button>
                    	</form>
	                </div>
	            </div>
	        </div>
	        <div class="col-lg-2"></div>
    	</div>
	</div>


	<div class="container">
    	<div class="row">
    		<div class="col-lg-2"></div>
        	<div class="col-lg-8">
          		<h2>Post Title</h2>
		        <!-- Date/Time -->
		        <p>Posted on January 1, 2017 at 12:00 PM</p>
		        <!-- Preview Image -->
		        <p><img class="img-fluid rounded" src="http://placehold.it/900x300" alt=""></p>
		        <!-- Post Content -->
		        <p id="text">Text</p>
		        <hr>
			</div>
			<div class="col-lg-2"></div>
		</div>
	</div>

	<script src="js/topPage.js"></script>
</body>
</html>