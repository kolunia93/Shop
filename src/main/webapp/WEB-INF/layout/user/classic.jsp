<%@  page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<security:csrfMetaTags/>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="/resources/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/styles.css">
<script src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/chosen.jquery.min.js"></script>
<link rel="stylesheet" href="/resources/css/chosen.min.css">
<script type="text/javascript">
	$(function() {
		$("select").chosen();
	});
</script>
<style type="text/css">
body {
	padding-bottom: 70px; 
	padding-top: 70px;
	 border: 2px solid black;
}
</style>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
    <tiles:insertAttribute name="header" />
	<div class="container">
		<tiles:insertAttribute name="body" />
	</div>
		<tiles:insertAttribute name="footer" />
</body>
</html>
