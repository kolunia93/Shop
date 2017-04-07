<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/resources/css/form.css">
		<form:form class="form-horizontal" action="/login" method="POST" onsubmit="return validate(this)">
			 <table>
            <tr> 
                 <td>Login</td>
                 <td>
      		<input name="login"id="login">
                 </td>
            </tr>
            <tr>
                 <td>Password</td>
                 <td><input type="password" name="password" id="password"></td>
            </tr>
            <tr>
                <td>
  				<input name="remember-me" type="checkbox" class="form-control"><label>Remember me</label>
                </td>
            </tr>
                   
            </table>
            <input type="submit"  value="Проверить">
            </form:form>
        
        <script >
    function showError(container, errorMessage) {
      container.className = 'error';
      var msgElem = document.createElement('span');
      msgElem.className = "error-message";
      msgElem.innerHTML = errorMessage;
      container.appendChild(msgElem);
    }
              function resetError(container) {
      container.className = '';
      if (container.lastChild.className == "error-message") {
        container.removeChild(container.lastChild);
      }
    }
           
      function validate(form) {
      var elems = form.elements;

      resetError(elems.login.parentNode);
      resetError(elems.password.parentNode);
      if (!elems.login.value) {
        showError(elems.login.parentNode, ' Введіть логін.');
      }   
      if (!elems.password.value) {
        showError(elems.password.parentNode, ' Введіть пароль.');
      }
      
      if((!elems.password.value)&&(!elems.login.value)){
    	  return false;
      }
      
        /* else if (elems.password.value.size<min) {
            showError(elems.password.parentNode, ' пароль.');
            
            return false;

         } */
      }
        </script>