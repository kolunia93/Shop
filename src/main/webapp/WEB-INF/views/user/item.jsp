
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	 <%@page import ="java.security.Principal"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<link rel="stylesheet" href="/resources/css/styles.css">
		
		<style type="text/css">


.con {position:relative}
.top {position:absolute;top:0;left:0;}

</style>
<div class="row">
	<div class="container">
	<div class="text-left col-md-6 col-xs-6 con">
					 <img class="img-rounded" width="80%" src="/images/item/${item.id}.jpg?version=${item.version}" >
					 <c:if test="${item.remain==0}">
					 <img src="/resources/img/remain.png" class="top" width="20%" >
					 </c:if>
		</div>
		<div class="text-left col-md-6 col-xs-6">
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Назва тоару :</div>  <div class="text-right col-md-6 col-xs-6" >${item.name.name}</div></div>
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Ціна в грн :</div>  <div class="text-right col-md-6 col-xs-6" > ${item.price}</div></div>
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Категорія :</div>  <div class="text-right col-md-6 col-xs-6" >${item.category.category}</div></div>
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Стать:</div>  <div class="text-right col-md-6 col-xs-6" > ${item.gender}</div></div>
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Виробник  :</div>  <div class="text-right col-md-6 col-xs-6" > ${item.vender.vender} ${item.vender.country.country}</div></div>
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Роки від/до  :</div>  <div class="text-right col-md-6 col-xs-6" >${item.years.min}/${item.years.max}</div></div>
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Сезон :</div>  <div class="text-right col-md-6 col-xs-6" > ${item.season}</div></div>
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Розмір від/до :</div>  <div class="text-right col-md-6 col-xs-6" >${item.size.min}/${item.size.max}</div></div>
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Про товар  :</div>  <div class="text-right col-md-6 col-xs-6" > ${item.descript.descript}</div></div>
			<c:if test="${item.remain==0}">
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Товар під замовлення ! </div></div>
			</c:if>
		<security:authorize access="isAuthenticated()">
		<a class="btn btn-danger" href="/buy/${item.id}">В корзину</a>
		</security:authorize>
		<security:authorize access="!isAuthenticated()">
		<a class="btn btn-danger" href="/login">В корзину</a>
		</security:authorize>
		</div>
	
</div>
</div>
<div class="container">

<div class="row">
	<form:form class="form-horizontal" action="/comment" method="POST" modelAttribute="comment" id="comment">
			<form:errors path="*"/>
      				<form:input class="form-control" type="text" path="comment" id="comment"/>
    			
    				<form:input class="form-control" type="hidden" path="item" value="${item.id}"/>
  			
    			
      				<button type="submit" class="btn btn-default" >Коментувати</button>
		</form:form>
		</div>
		<div class="jumbotron">
		<c:forEach items="${comments}" var="comment">
		<div class="row down_line">
		<div class="col-md-10 col-xs-10 "><p>${comment.comment}</p></div>
		<div class="col-md-2 col-xs-2"><h6>${comment.date}</h6><br><h6>${comment.user.username}</h6>
		<security:authorize access="isAuthenticated() and principal.username=='${comment.user.username}' or hasRole('ROLE_ADMIN')">
		 <a href="/deleteComment/${comment.id}">Видалити коментар</a>
		</security:authorize> 
		</div>
		</div>
		</c:forEach>
	</div>
</div>
 