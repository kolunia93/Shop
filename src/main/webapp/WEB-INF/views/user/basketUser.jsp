<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	
	
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="favicon/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">	
	
	<link rel="stylesheet" href="./Furniture House - Furniture Shopping Template_files/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/csss/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/resources/csssfont-awesome.min.css">
    <link rel="stylesheet" href="/resources/csss.carousel.css">    
    <link rel="stylesheet" type="text/css" href="/resources/csss/lightbox.css" media="screen">
    <link rel="stylesheet" type="text/css" href="/resources/csss/flexslider.css" media="screen">
    <!--Fonts-->
    <link href="/resources/csss" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/csss/style.css">
    <link rel="stylesheet" href="/resources/csss/responsive.css">

<div class="row margin_20">
	<div class="col-sm-12 col-xs-12">
	<div class="row">		
	<div class="col-md-4 col-xs-4"></div>
	<div class="col-md-3 col-xs-3">Назва товару</div>
	<div class="col-md-2 col-xs-2">Ціна товару </div>
	</div>
	<c:forEach items="${user.items}" var="item" >
	<div class="row down_line margin_20">
	<div class="col-md-4 col-xs-4">
	<img class="img-rounded" width="25%" src="/images/item/${item.id}.jpg?version=${item.version}" >
	</div>
	<div class="col-md-3 col-xs-3">
	${item.name.name}
	</div>
	<div class="col-md-2 col-xs-2">
	${item.price} грн.	
	</div>
	
	<div class="col-md-3 col-xs-3">
<div class="row m0 proBuyBtn">
	<a class="btn  btn-danger" href="/deletebuy/${item.id}" >Видалити</a>
</div>
	</div>
	</div>
	</c:forEach>
<div class="col-md-4 col-xs-4">
<h3>загальна сума замовлення</h3>
</div>
<div class="col-md-4 col-xs-4">
<h3>${sum}  грн.</h3>
</div>
<div class="col-md-4 col-xs-4">

	<a class="btn btn-success" href="/order"  >сформувати замовлення</a>
	</div>		 
		
	</div>
</div>