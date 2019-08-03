<%@include file="/WEB-INF/views/include.jsp"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<style>

<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">

.error{
color:red;
}
form {
	margin: 0 auto;
	width: 300px
}

input {
	margin-bottom: 3px;
	padding: 10px;
	width: 100%;
	border: 1px solid #CCC
}

button {
	padding: 10px
}

label {
	cursor: pointer
}

#form-switch {
	display: none
}

#register-form {
	display: none
}

#form-switch:checked ~#register-form {
	display: block
}

#form-switch:checked ~#login-form {
	display: none
}
</style>
</head>
<body class="backGround">
	
		<input type='checkbox' id='form-switch'>
	<form:form id='login-form' action="validateLogin" modelAttribute="user">
		<h1>Welcome to Login Page</h1>
		<p class="error">${loginMessage}</p>
		<p class="error">${invaliadUserMessage}</p>
		UserName:<form:input path="userName" placeholder="Username" required="true"></form:input>
		 UserPassword:<form:input path="userPassword" type="password" placeholder="Password" required="true"></form:input>
		<button type='submit'>Login</button>
		<a href="/Project/Register/">Register</a>
		
	</form:form>
	
</body>
</html>