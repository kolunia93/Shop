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
					<li class="active"><a href="/admin/item">Item</a><span class="sr-only">(current)</span>	</li>
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
<div class="row">
<div class="col-sm-3 col-xs-3">
Товар має звязки що не дозволяють його видалити. Спочатку видаліть звязки.	
</div>
<div class="col-sm-3 col-xs-3">
наступні користувачі добавили товар у корзину
</div>
<div class="col-sm-3 col-xs-3">
у товара є наступні коментарі
</div>
<div class="col-sm-3 col-xs-3">
Існують наступні замовлення з данним товаром
</div>
</div>
<div class="row margin_20 down_line">
<div class="col-sm-3 col-xs-3">
[id=
${items.id}] ,${items.name.name}
<a class="btn  btn-danger" href="/admin/item/deleteDependency/${items.id}" >Видалити</a>
<br>
</div>
<div class="col-sm-3 col-xs-3">
<c:forEach items="${users}" var="user" >
${user.username}<br>
</c:forEach>
</div>
<div class="col-sm-3 col-xs-3">
<c:forEach items="${comments}" var="comment" >
${comment.id}

</c:forEach>
</div>
<div class="col-sm-3 col-xs-3">
<c:forEach items="${orders}" var="order" >
${order.id}

</c:forEach>
</div>

</div>






