<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


 
<c:url var="baseURL" value="/" />

<div class="container">
<div class="row">
	<div class="col-sm-4">
                    <form:form method="post" modelAttribute="loginForm"  action="${baseURL}authn/processLogin">
                                <div class="form-group">
                                    <form:label path="userEmail">User Email:</form:label>
                                    <form:input   class="form-control" path="userEmail" />
                                </div>
			       <div class="form-group">
                                    <form:label path="password">Password</form:label> 
                                    <form:input type="password"  class="form-control"  path="password" />
                                </div>
			        
                                  <form:input type="hidden"    path="request" />
 

                                <div class="form-group">
			       <div class="col-md-4">
				<input class="btn btn-primary" type="submit" value="Login" />
                               </div>
                               </div>
	 
</form:form>
	</div>
 </div>
 


</div>

 
 
 