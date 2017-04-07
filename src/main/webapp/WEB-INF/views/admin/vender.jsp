<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
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
					<li class="active"><a href="/admin/vender">Vender</a><span class="sr-only">(current)</span></li>
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
	<div class="col-md-3 col-xs-12">
	 <form:form class="form-horizontal" action="/admin/vender" method="GET" modelAttribute="filter" id="filter">
				<custom:hiddenInputs excludeParams=" country, vender" />
				<div class="form-group">
					<div class="col-sm-6 col-xs-6">
	      				<form:input type="text" class="form-control" path="vender" placeholder="vender"/>
	    			</div>
	    			<div class="col-sm-6 col-xs-6">
	    		
	    			
				  
				  <div class="col-sm-12">
						<form:checkboxes class="checkbox inline" items="${countrys}" path="countryId" itemLabel="country" itemValue="id"/>
					</div>
				
				  </div>
				</div>
				<div class="form-group">
    				<div class="col-sm-12">
      					<button type="submit" class="btn btn-primary">Search</button>
    				</div>
  				</div>
				
			</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/vender" method="POST" modelAttribute="vender">
					<div class="form-group">
    					<label for="countryId" class="col-sm-2 control-label">country</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="country" id="countryId" items="${countrys}" itemValue="id" itemLabel="country"/>
    					</div>
  					</div>
					<div class="form-group">
    					<label for="vender" class="col-sm-2 control-label">vender</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="vender" id="vender"/>
    					</div>
    					<label for="venderId" class="col-sm-offset-2 col-sm-10"><form:errors path="vender"/></label>
  					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default">Create</button>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3 col-xs-3"><h3>Vender</h3></div>
			<div class="col-md-3 col-xs-3"><h3>country</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Update</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Delete</h3></div>
		</div>
			<c:forEach items="${page.content}" var="vender">
				<div class="row">
					<div class="col-md-3 col-xs-3">${vender.vender}</div>
					<div class="col-md-3 col-xs-3">${vender.country.country}</div>
					<div class="col-md-3 col-xs-3"><a class="btn btn-warning" href="/admin/vender/update/${vender.id}">update</a></div>
					<div class="col-md-3 col-xs-3"><a class="btn btn-danger" href="/admin/vender/delete/${vender.id}">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
<div class="row">
			<div class="col-md-6 col-xs-6 text-center">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="country"/>
						<custom:sort innerHtml="Name desc" paramValue="country,desc"/>
						<custom:sort innerHtml="Price asc" paramValue="vender"/>
						<custom:sort innerHtml="Price asc" paramValue="vender,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6 col-xs-6 text-center">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>