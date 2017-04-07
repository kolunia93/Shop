<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	#filter>.form-group>.col-sm-12>span{
		display: block;
	}
</style>
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
					<li><a href="/admin/category">Category</a></li>
					<li><a href="/admin/country">Country</a></li>
					<li><a href="/admin/name">Name</a></li>
					<li ><a href="/admin/vender">Vender</a></li>
					<li><a href="/admin/years">Years</a></li>
					<li><a href="/admin/size">Size</a></li>
					<li><a href="/admin/descript">Descript</a></li>
					<li class="active"><a href="/admin/item/basket">Basket</a><span class="sr-only">(current)</span></li>
				</ul>
			</div>
		</div>
	</nav>
</div>


<div class="row">
	<div class="col-sm-12 col-xs-12">
	<div class="row">
		<div class="col-sm-1 col-xs-1"> Номер замовлення :</div>
		<div class="col-sm-1 col-xs-1"> Статус заявки :</div>
		<div class="col-sm-2 col-xs-2"> дата :</div>
		<div class="col-sm-3 col-xs-3"> Користувач :</div>
		<div class="col-sm-3 col-xs-3"> Список товарів :</div>
		<div class="col-sm-2 col-xs-2"> </div>
	
	</div>
			<c:forEach items="${orders}" var="order" >
			<div class="row margin_20 down_line">
			<div class="col-sm-1 col-xs-1"> ${order.id}</div>
			<div class="col-sm-1 col-xs-1"> ${order.order}</div>
			<div class="col-sm-2 col-xs-2">  ${order.date}</div>
			<div class="col-sm-3 col-xs-3"> ${order.userOrder.username}</div>
		<div class="col-sm-3 col-xs-3">  <c:forEach items="${order.items}" var="item" >
		
	${item.name.name}
	[id= ${item.id} ] ,
	</c:forEach> </div>
	<div class="col-sm-2 col-xs-2"> <a class="btn btn-success" href="/admin/item/finish/${order.id}">Провести</a></div>
		</div>
		
		</c:forEach>
		
	</div>
</div>
 <a class="btn btn-primary" href="/admin/item/basketAll">Проведені заявки</a>
  <a class="btn btn-primary" href="/admin/item/basket">Нові заявки</a>