<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<div class="row">

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/admin/item">Item</a></li>
					<li ><a href="/admin/category">Category</a></li>
					<li><a href="/admin/country">Country</a></li>
					<li><a href="/admin/name">Name</a></li>
					<li><a href="/admin/vender">Vender</a></li>
					<li><a href="/admin/years">Years</a></li>
					<li><a href="/admin/size">Size</a></li>
					<li><a href="/admin/descript">Descript</a></li>
					<li><a href="/admin/item/basket">Basket</a></li>
					
				</ul>
			</div>
		</div>
	</nav>
</div>
<h2 align="center">Ви не можете видалити стандартний обєкт!</h2>