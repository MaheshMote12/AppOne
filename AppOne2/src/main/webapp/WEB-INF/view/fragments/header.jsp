<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>  
 


 <script type="text/javascript">

$(document).ready(function(){
	 
	  $("#logout").click(function(e){
		 e.preventDefault();
		 $("#logout-form").submit();
	  });
});


</script>

	<nav class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<a href="<spring:url value="/"/>" class="navbar-brand">file upload</a>
			</div>
			<ul class="nav navbar-nav">
				<%-- <li><a href="<spring:url value="/services/"/>">Services</a></li>
				<li><a href="<spring:url value="/appointments/"/>">Appointments</a></li>
				<li><a href="<spring:url value="/schedule/"/>">Schedule</a></li> --%>
				
				<sec:authorize access="authenticated" var="authenticated"/>
				<c:choose>
					<c:when test="${authenticated}">
						<li>
							<p class="navbar-text">
								Welcome
								<sec:authentication property="name"  />
								<a id="logout" href="#">Logout</a>
							</p>
							<form id="logout-form" action="<c:url value="/logout"/>" method="post">
								<sec:csrfInput/>
							</form>
						</li>	
					</c:when>
					<c:otherwise>
						<li><a href="<spring:url value="/login/"/>">Sign In</a></li>			
						<li><a href="<spring:url value="/register/"/>">Register</a></li>	
					</c:otherwise>
				</c:choose>
				
				
			</ul>
		</div>
	</nav>