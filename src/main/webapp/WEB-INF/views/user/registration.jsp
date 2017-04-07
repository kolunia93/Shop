<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 
<div class="row">
	<div class="col-sm-12 col-xs-12">
		<form:form class="form-horizontal" action="/registration" method="POST" modelAttribute="formm" id="userForm">
			
			<div class="form-group">
				<label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="username"/></label>
			</div>
			<div class="form-group">
    			<label for="name" class="col-sm-2 control-label">Login</label>
    			<div class="col-sm-10">
      				<form:input type="text" required="required" pattern="^[A-Za-z0-9_]{1,15}$" path="username" id="name"/>
    			</div>
  			</div>
  			<div class="form-group">
				<label for="email" class="col-sm-offset-2 col-sm-10"></label>
			</div>
			<div class="form-group">
    			<label for="email" class="col-sm-2 control-label">Email</label>
    			<div class="col-sm-10">
      				<form:input class="form-control"  type="email" path="email" id="email"/>
    			</div>
    			<form:errors path="email"/>
  			</div>
  			
			<div class="form-group">
    			<label for="password" class="col-sm-2 control-label">Password</label>
    			<div class="col-sm-10">
      				<form:password class="form-control" path="password" id="password"/>
      				<form:errors path="password"/>
    			</div>
  			</div>
  			<div class="form-group">
    			<label for="password" class="col-sm-2 control-label">Номер телефону</label>
    			<div class="col-sm-10">
      				<form:input type="tel" path="fone" pattern="^\d{3}-\d{2}-\d{2}-\d{3}$" title="Введіть данні в наступному форматі: кож оператора + номер телефона. (095-22-22-222)"/>
    			</div>
    			<form:errors path="fone"/>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<button type="submit" class="btn btn-default" >Register</button>
    			</div>
  			</div>
		</form:form>
	</div>
</div>
