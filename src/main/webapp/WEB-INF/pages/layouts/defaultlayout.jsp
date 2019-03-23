<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="icon" type="image/png" href='<c:url  value="/static/assets/paper_img/favicon.ico"></c:url>'>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>Paper Kit by Creative Tim</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <link href='<c:url  value="/static/bootstrap3/css/bootstrap.css"></c:url>' rel="stylesheet" />
    <link href='<c:url  value="/static/assets/css/ct-paper.css"></c:url>' rel="stylesheet"/>
    <link href='<c:url  value="/static/assets/css/demo.css"></c:url>' rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

</head>

<body>
<div class="wrapper">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>
</body>

<script src='<c:url value="/static/assets/js/jquery-1.10.2.js"></c:url>' type="text/javascript"></script>
<script src='<c:url value="/static/assets/js/jquery-ui-1.10.4.custom.min.js"></c:url>' type="text/javascript"></script>
<script src='<c:url value="/static/bootstrap3/js/bootstrap.js"></c:url>' type="text/javascript"></script>
<!--  Plugins -->
<script src='<c:url value="/static/assets/js/ct-paper-checkbox.js"></c:url>'></script>
<script src='<c:url value="/static/assets/js/ct-paper-radio.js"></c:url>'></script>
<script src='<c:url value="/static/assets/js/bootstrap-select.js"></c:url>'></script>
<script src='<c:url value="/static/assets/js/bootstrap-datepicker.js"></c:url>'></script>
<script src='<c:url value="/static/assets/js/ct-paper.js"></c:url>'></script>

</html>