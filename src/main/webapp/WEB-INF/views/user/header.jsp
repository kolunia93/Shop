<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import ="java.security.Principal"%>
    <%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="ua.entity.User"%>
<%@page import="ua.entity.Season"%>
<%@page import="ua.entity.Gender"%>

<link rel="stylesheet" href="./Furniture House - Furniture Shopping Template_files/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/csss/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/resources/csssfont-awesome.min.css">
    <link rel="stylesheet" href="/resources/csss.carousel.css">    
    
    <link rel="stylesheet" type="text/css" href="/resources/csss/lightbox.css" media="screen">
    <link rel="stylesheet" type="text/css" href="/resources/csss/flexslider.css" media="screen">
    
    <!--Fonts-->
    <link href="/resources/csss" rel="stylesheet" type="text/css">
    
    <!--Mechanic Styles-->
    <link rel="stylesheet" href="/resources/csss/style.css">
    <link rel="stylesheet" href="/resources/csss/responsive.css">
 <style type="text/css">
        
    
   .btn-link{
  border:none;
  outline:none;
  background:none;
  cursor:pointer;
  color:#0000EE;
  padding:0;
  text-decoration:underline;
  font-family:inherit;
  font-size:inherit;
} 
</style>
 <header class="row" id="header">
<div class="row m0 top_menus">
            <div class="navbar navbar-fixed-top padd">
            <div class="container">
                <div class="row">
                    <ul class="nav nav-pills fleft">
                        <li><a href="/">Усі товари</a></li>
                        <li><a href="/boy">Хлопчик</a></li>
                        <li><a href="/girls">Дівчинка</a></li>
                      
                    </ul>
                    <ul class="nav nav-pills fright">
                    <li>
                        <security:authorize access="hasRole('ROLE_ADMIN')">
					    		<a href="/admin">ADMIN</a>
					    	</security:authorize>
					    	</li>
                        <li>
	                        
                        <security:authorize access="isAuthenticated()">
	                       		<a href="">Привіт, <security:authentication property="principal.username"/></a>
                        </security:authorize>
                        </li>
                        <li>
                        <security:authorize access="!isAuthenticated()">
	                       <a href="/login">Вхід</a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
	                       		 <a href="/basketUser">Корзина <c:if test="${user.count!=0}">(${user.count})</c:if></a>
                        </security:authorize>
                        
                        </li>
                        <li>
                        <security:authorize access="!isAuthenticated()">
	                       <a href="/registration">Реєстрація</a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
	                       		<form:form  action="/logout" method="POST" id="myform">
	                       		<a href="#" onclick="document.getElementById('myform').submit()">Вихід</a>
	                     
	                    </form:form>
                        </security:authorize>
                        </li>
                    </ul>
                </div>
                </div>
            </div>
        </div>
        <nav class="navbar navbar-default m0 navbar-static-top ">
            <div class="container-fluid container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed " data-toggle="collapse" data-target="#mainNav">
                        <i class="fa fa-bars"></i> Navigation
                    </button>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="mainNav" tabindex="5000" style="overflow: hidden; outline: none;">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="/"  >
                           		 Головна
                            </a>
                        </li>
                        
                       	<li class="dropdown">
                            <a  class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            Категорії
                            </a>
                            <ul class="dropdown-menu" role="menu">
                            

			<c:forEach items="${categorys}" var="category">
				   <li><a href="/category/${category.id}">
				   ${category.category}
				   </a></li>
				 </c:forEach>	

                            

                            </ul>
                        </li>
                        
                        	<li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            	Виробник
                            </a>
                            <ul class="dropdown-menu" role="menu">
                            
                            
			<c:forEach items="${venders}" var="vender">
				  <li><a href="/vender/${vender.id}">
				   	${vender.vender} (	${vender.country.country} )
				   </a></li>
				 </c:forEach>	
				 
				 

                            

                            </ul>
                        </li>
                        <li class="dropdown">
                            <a  class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            	Сезон
                            </a>
                            <ul class="dropdown-menu" role="menu">
                            
                       <c:set var="enumValues" value="<%=Season.values()%>"/><c:forEach items="${enumValues}" var="season">   

				   <li><a href="/season/${season}">
				   	${season} 
				   </a></li>
				 </c:forEach>	
				 
				
                            

                            </ul>
                        </li>
                        
                        
                    </ul>              
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
</header>
