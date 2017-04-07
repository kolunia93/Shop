<%@page import="ua.entity.Season"%>
<%@page import="ua.entity.Gender"%>
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
					<li class="active"><a href="/admin/item">Item</a><span class="sr-only">(current)</span></li>
					<li><a href="/admin/category">Category</a></li>
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
	<div class="col-md-2 col-xs-12">
	
	<form:form class="form-horizontal" action="/admin/item" method="GET" modelAttribute="filter" id="filter">
	<div class="form-group">
				<custom:hiddenInputs excludeParams="minPrice,maxPrice,categoryId,genderSerch,search,minSize,maxSize,seasonSerch,venderId" />
				
				 
					
	    			<div class="col-sm-12 col-xs-12">
	      				<form:input type="text" class="form-control" path="search" placeholder="Name"/>
	    			</div>
	    			
					
	    			<div class="col-sm-12 col-xs-12">
	      				<form:input type="text" class="form-control" path="minPrice" placeholder="Min price"/>
	    			</div>
	    			<div class="col-sm-12 col-xs-12">
	      				<form:input type="text" class="form-control" path="maxPrice" placeholder="Max price"/>
				</div>
				<div class="col-sm-12 col-xs-12">
	      				<form:input type="text" class="form-control" path="minSize" placeholder="Min size"/>
	    			</div>
	    			<div class="col-sm-12 col-xs-12">
	      				<form:input type="text" class="form-control" path="maxSize" placeholder="Max size"/>
				</div>
    			<div class="col-sm-12 col-xs-12">
    				  <form:select class="form-control"  multiple="False"  path="categoryId" id="category" items="${categorys}" itemValue="id" itemLabel="category"/>
      			</div>
      			<div class="col-sm-12 col-xs-12">
      			
    				  <form:select class="form-control" tabindex="qqq"  multiple="False" path="venderId" id="vender" items="${venders}" itemValue="id" itemLabel="vender"/>
      			</div>
				
				 
				<form:checkboxes   multiple="False" path="genderSerch" id="gender" items="${genders}" itemValue="id" itemLabel="description"/>
				
    					 
    			
							<br>
							 <form:checkboxes   multiple="False" path="seasonSerch" id="season" items="${seasons}" itemValue="id" itemLabel="description"/> 
								
							
				<div class="form-group">
    				<div class="col-sm-12">
      					<button type="submit" class="btn btn-primary">Search</button>
    				</div>
  				</div>
				</div>
			</form:form>
	
	</div>
	<div class="col-md-8 col-xs-12">
	<c:if test="${item ne null}">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/item" method="POST" modelAttribute="item" enctype="multipart/form-data">
					<form:errors path="*"/>
					<div class="form-group">
    					<div class="row">
    					<div class="col-md-6 col-xs-6">
    					
    					<label for="nameId" class="col-sm-2 control-label">Name</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="name" id="nameId"/>
    					</div>
      					
      					<label for="priceId" class="col-sm-2 control-label">Price</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="price" id="priceId"/>
      					</div>
      					
      					<label for="price" class="col-sm-offset-2 col-sm-10"><form:errors path="price"/></label>
      						
      					<label for="categoryId" class="col-sm-2 control-label">Category</label>
    					<div class="col-sm-10">
    					  <form:select class="form-control" path="category" id="nameId" items="${categorys}" itemValue="id" itemLabel="category"/>
      					</div>
      					
      					<label for="venderId" class="col-sm-2 control-label">vender</label>
    					<div class="col-sm-10">
      						 <form:select class="form-control" path="vender" id="venderId" items="${venders}" itemValue="id" itemLabel="vender" />
      					</div>
      					
      					<label for="remainId" class="col-sm-2 control-label">Remain</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="remain" id="remainId"/>
      					</div>
      					<label for="remainId" class="col-sm-offset-2 col-sm-10"><form:errors path="remain"/></label>
      					
    					<label for="genderId" class="col-sm-2 control-label">Gender</label>
    					<div class="col-sm-10">
				    	 <c:set var="enumValues" value="<%=Gender.values()%>"/><c:forEach items="${enumValues}" var="gender"> </c:forEach>
    					  
    					  <form:select class="form-control" path="gender" id="genderId" items="${enumValues}" />
      					</div>
      					
      					</div>
      					
      					
      					<div class="col-md-6 col-xs-6">
      					<label for="yearsMin" class="col-sm-2 control-label">Years_min</label>
      					<div class="col-sm-10">
    						<form:input type="text" class="form-control" path="minYears" id="yearsMin"/>
      					</div>
      					<label for="years" class="col-sm-offset-2 col-sm-10"><form:errors path="minYears"/></label>
      					
      					<label for="yearsMax" class="col-sm-2 control-label">Years_max </label>
      					<div class="col-sm-10">
    						<form:input type="text" class="form-control" path="maxYears" id="yearsMax"/>
      					</div>
      					<label for="years" class="col-sm-offset-2 col-sm-10"><form:errors path="maxYears"/></label>
      					<label for="sizeMin" class="col-sm-2 control-label">Size_min</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="sizeMin" id="sizeMin"/>
      					</div>
      					<label for="size" class="col-sm-offset-2 col-sm-10"><form:errors path="sizeMax"/></label>
      					<label for="sizeId" class="col-sm-2 control-label">Size_max</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="sizeMax" id="sizeMax"/>
      					</div>
      					<label for="size" class="col-sm-offset-2 col-sm-10"><form:errors path="sizeMax"/></label>
      					
      					<label for="descriptId" class="col-sm-2 control-label">descript </label>
      					<div class="col-sm-10">
    						<form:input type="text" class="form-control" path="descript" id="descriptId"/>
      					</div>
      					
      					<label for="seasonId" class="col-sm-2 control-label">Season</label>
    					<div class="col-sm-10">
    					
    					 <c:set var="enumValues" value="<%=Season.values()%>"/>
						
						<c:forEach items="${enumValues}" var="season">
    					 
    					  </c:forEach>
    					  
    					  <form:select class="form-control" path="season" id="seasonId" items="${enumValues}" />
      					</div>
    					</div>
    					</div>
  					</div>
  					<div class="form-group">
    					
    					
    					<div class="col-sm-offset-2 col-sm-10 text-left">
		  					<label class="btn btn-default btn-file">
		        				Browse <input type="file" name="file" style="display: none;">
		    				</label>
	    				</div>
	    				<div class="col-sm-offset-2 col-sm-10 margin_20 text-left">
      						<button type="submit" class="btn btn-default ">Create</button>
    					</div>	
    					</div>
  					
				</form:form>
			</div>
		</div>
		</c:if>
	</div>
</div>	

		<div class="row">
		<div class="col-md-1 col-xs-1"><h4>Pictures</h4></div>
			<div class="col-md-1 col-xs-1"><h4>name</h4></div>
			<div class="col-md-1 col-xs-1"><h4>price</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Category</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Vendor</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Gender</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Years min/max</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Size min/max</h4></div> 			
			<div class="col-md-1 col-xs-1"><h4>Season</h4></div>
			<div class="col-md-2 col-xs-2"><h4>Update</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Delete</h4></div>
		</div>
		<div class="row">
			<c:forEach items="${page.content}" var="item">
				<div class="row margin_20 down_line">
					<div class="col-md-1 col-xs-1"><img class="img-rounded" width="100%" src="/images/item/${item.id}.jpg?version=${item.version}"></div> 
					<div class="col-md-1 col-xs-1">${item.name.name}</div>
					<div class="col-md-1 col-xs-1">${item.price}</div>
					<div class="col-md-1 col-xs-1">${item.category.category}</div>
					<div class="col-md-1 col-xs-1">${item.vender.vender} ${item.vender.country.country}</div>
					<div class="col-md-1 col-xs-1">${item.gender}</div>
					<div class="col-md-1 col-xs-1">${item.years.min}/${item.years.max}</div>
					<div class="col-md-1 col-xs-1">${item.size.min}/${item.size.max}</div>
					
					<div class="col-md-1 col-xs-1">${item.season}</div>
					<div class="col-md-2 col-xs-2"><a class="btn btn-warning" href="/admin/item/update/${item.id}<custom:allParams/>">update</a></div>
					<div class="col-md-1 col-xs-1"><a class="btn btn-danger" href="/admin/item/delete/${item.id}<custom:allParams/>">delete</a></div>
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
						<custom:sort innerHtml="Name asc" paramValue="name"/>
						<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
						<custom:sort innerHtml="Price asc" paramValue="price"/>
						<custom:sort innerHtml="Price asc" paramValue="price,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6 col-xs-6 text-center">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
			</div>
		</div>
	</div>

<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>