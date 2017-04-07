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
					<li><a href="/admin/vender">Vender</a></li>
					<li class="active"><a href="/admin/years">Yaers</a><span class="sr-only">(current)</span></li>
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
	<form:form class="form-horizontal" action="/admin/years" method="GET" modelAttribute="filter" id="filter">
				<custom:hiddenInputs excludeParams=" minYears, maxYears" />
				<div class="form-group">
					<div class="col-sm-6 col-xs-6">
	      				<form:input type="text" class="form-control" path="minYearsSerch" placeholder="Min Years"/>
	    			</div>
	    			<div class="col-sm-6 col-xs-6">
	      				<form:input type="text" class="form-control" path="maxYearsSerch" placeholder="Max Yize"/>
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
				<form:form class="form-horizontal" action="/admin/years" method="POST" modelAttribute="years">
					<div class="form-group">
    					<label for="yearsMin" class="col-sm-2 control-label">years MIN</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="minYears" id="yearsMin"/>
    					</div>
    					<label for="min" class="col-sm-offset-2 col-sm-10"><form:errors path="minYears"/></label>
    					<label for="yearsMax" class="col-sm-2 control-label">years MAX</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="maxYears" id="yearsMax"/>
    					</div>
    					<label for="maxYears" class="col-sm-offset-2 col-sm-10"><form:errors path="maxYears"/></label>
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
			<div class="col-md-3 col-xs-3"><h3>years min</h3></div>
			<div class="col-md-3 col-xs-3"><h3>years max</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Update</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Delete</h3></div>
		</div>
			<c:forEach items="${page.content}" var="years">
				<div class="row">
<!-- 					тут все так само як на сервлетах -->
					<div class="col-md-3 col-xs-3">${years.min}</div>
					<div class="col-md-3 col-xs-3">${years.max}</div>
					<div class="col-md-3 col-xs-3"><a class="btn btn-warning" href="/admin/years/update/${years.id}">update</a></div>
					<div class="col-md-3 col-xs-3"><a class="btn btn-danger" href="/admin/years/delete/${years.id}">delete</a></div>
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
						<custom:sort innerHtml="Name asc" paramValue="min"/>
						<custom:sort innerHtml="Name desc" paramValue="min,desc"/>
						<custom:sort innerHtml="Price asc" paramValue="max"/>
						<custom:sort innerHtml="Price asc" paramValue="max,desc"/>
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