<%@include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<style>
.error {
	color: red;
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
<body>

	<form:form action="${pageContext.request.contextPath}/RegisterSucess" method="POST" modelAttribute="user1" >
		<h1>Registration Page</h1>
       FullName:    <form:input path="userFullName" placeholder="FullName"></form:input>
		<br><form:errors path="userFullName" cssClass="error" /></br>
	   Password:   <form:input path="userPassword" type="password"  placeholder="Password"></form:input>
		<br><form:errors path="userPassword" cssClass="error" /></br>
	   UserName:   <form:input path="userName" placeholder="UserName"></form:input>
		<p class="error">${invalidUserName}</p>
		<form:errors path="userName" cssClass="error"/>
	   Aadhar Number:   <form:input path="userAdharNum" placeholder="Aadhar"></form:input> 
 		<p class="error"> ${invalidAdhar} </p>
		<form:errors path="userAdharNum" cssClass="error" />
	   Phone Number:  <form:input path="userPhoneNum" placeholder="Phone Number"></form:input>
		<p class="error">${invalidPhoneNum}</p>
		<form:errors path="userPhoneNum" cssClass="error" />
	   Email Id:  <form:input path="userEmailId" placeholder="EmailId"></form:input>
		 <p class="error">${invalidEmailId}</p>
		<form:errors path="userEmailId" cssClass="error" />
		<input type="submit" value="Register"/>
		<a href="loginPage">Already Registered?Sign In</a>
	</form:form>
</body>
</html>