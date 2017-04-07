<%@page import="ua.entity.Season"%>
<%@page import="ua.entity.Gender"%>
<%@page import="ua.entity.User"%>
<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<link rel="stylesheet" href="/resources/css/styles.css">
	
<style type="text/css">
   .centerLayer {
    width: 100%; /* Ширина слоя в пикселах */
    margin: 0 auto; /* Отступ слева и справа */
    padding: 10px; /* Поля вокруг текста */
    text-align: center; /* Выравнивание содержимого слоя по левому краю */
   }
   </style>

<div class="row margin_20">
	<div class="col-md-3 col-xs-3">
	<form:form class="form-horizontal"  method="GET" modelAttribute="filter" id="filter">
	<div class="form-group">
				<custom:hiddenInputs excludeParams="minPrice,maxPrice,categoryId,gender,search,minSize,maxSize,season,venderId" />

	    			<div class="col-sm-12 col-xs-12">
	      				<form:input type="text" class="form-control" path="search" placeholder="Name"/><br>
	    			</div>
	    			
	    			<div class="col-sm-12 col-xs-12">
	      				<form:input type="text" class="form-control" path="minPrice" placeholder="Min price"/><br>
	    			</div>
	    			<div class="col-sm-12 col-xs-12">
	      				<form:input type="text" class="form-control" path="maxPrice" placeholder="Max price"/><br>
				</div>
				<div class="col-sm-12 col-xs-12">
	      				<form:input type="text" class="form-control" path="minSize" placeholder="Min size"/><br>
	    			</div>
	    			<div class="col-sm-12 col-xs-12">
	      				<form:input type="text" class="form-control" path="maxSize" placeholder="Max size"/><br>
				</div>
    			
				<div class="form-group">
    				<div class="col-sm-12 text-center">
      					<button type="submit"  class="btn btn-primary">Search</button>
    				</div>
  				</div>
				</div>
			</form:form>
			<div class="dropdown text-center">
					<button class="btn btn-primary dropdown-toggle"  type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort  innerHtml="По імені"  paramValue="name"/>
						<custom:sort innerHtml="По імені, реверс" paramValue="name,desc"/>
						<custom:sort innerHtml="Від дешевих до дорогих" paramValue="price"/>
						<custom:sort innerHtml="Від дорогих до дешевих" paramValue="price,desc"/>
					</ul>
				</div>
			
			<div class="col-md-6 col-xs-6 text-center margin_20">
				<custom:size title="Відображати по" posibleSizes="3,6,12,18" size="${page.size}" />
			</div> 
			
	</div>
	<div class="col-md-9 col-xs-9">
	
	<div class="row margin_20">
		<c:forEach items="${page.content}" var="item">
	<c:if test="${item ne null}">
	
		<div class="col-md-4 col-xs-4 ">
		
		<div class="col-md-12 col-xs-12 con">
		<img class="img-rounded" width="100%" src="/images/item/${item.id}.jpg?version=${item.version}" >
		<c:if test="${item.remain==0}">
		<img src="/resources/img/remain.png" class="top" width="20%"  >
		</c:if>
		</div>
		<div class="row">
		<div class="col-md-12 col-xs-12">
		
			<div class="row down_line"> <div class="text-center col-md-12 col-xs-12" >${item.name.name}</div></div>
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Ціна в грн :</div>  <div class="text-right col-md-6 col-xs-6" > ${item.price}</div></div>
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Категорія :</div>  <div class="text-right col-md-6 col-xs-6" >${item.category.category}</div></div>
			<div class="row down_line"> <div class="text-left col-md-6 col-xs-6">Стать:</div>  <div class="text-right col-md-6 col-xs-6" > ${item.gender}</div></div>
			
			<security:authorize access="!isAuthenticated()">
			<div class="text-left col-md-6 col-xs-6"> <a class="btn btn-danger" href="/item/${item.id}<custom:allParams/>">Детальніше</a></div> 
			 </security:authorize>
			<security:authorize access="isAuthenticated()">
			 <div class=" col-md-6 col-xs-6 centerLayer"> <a class="btn btn-danger" href="/item/${item.id}<custom:allParams/>">Детальніше</a></div> 
			 <div class=" col-md-6 col-xs-6 centerLayer" > <a class="btn btn-success" href="/buy/${item.id}<custom:allParams/>">В корзину</a></div>
			</security:authorize>
			</div>
			</div>
			</div>
			</c:if>
		</c:forEach>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>