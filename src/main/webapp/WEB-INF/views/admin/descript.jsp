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
					<li><a href="/admin/category">Category</a></li>
					<li><a href="/admin/country">Country</a></li>
					<li ><a href="/admin/name">Name</a></li>
					<li><a href="/admin/vender">Vender</a></li>
					<li><a href="/admin/years">Years</a></li>
					<li><a href="/admin/size">Size</a></li>
					<li class="active"><a href="/admin/descript">Descript</a><span class="sr-only">(current)</span></li>
					<li><a href="/admin/item/basket">Basket</a></li>
					
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
	<form:form modelAttribute="filter" action="/admin/descript" method="get" class="form-inline">
			<div class="form-group">
				<form:input path="search" placeholder="search" class="form-control" />
				<custom:hiddenInputs excludeParams="search"/>
				<button type="submit" class="btn btn-primary">Ok</button>
			</div>
		</form:form></div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/descript" method="POST" modelAttribute="descript">
					<div class="form-group">
    					<label for="descriptId" class="col-sm-2 control-label">descript</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="descript" id="descriptId"/>
    					</div>
    					<label for="descript" class="col-sm-offset-2 col-sm-10"><form:errors path="descript"/></label>
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
			<div class="col-md-4 col-xs-4"><h3>descript</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Update</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Delete</h3></div>
		</div>
			<c:forEach items="${page.content}" var="descript">
				<div class="row">
					<div class="col-md-4 col-xs-4">${descript.descript}</div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-warning" href="/admin/descript/update/${descript.id}">update</a></div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-danger" href="/admin/descript/delete/${descript.id}">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12"></div>
<div class="col-md-2 col-xs-12">
		<div class="row">
			<div class="col-md-6 col-xs-6 text-center">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="descript"/>
						<custom:sort innerHtml="Name desc" paramValue="descript,desc"/>
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