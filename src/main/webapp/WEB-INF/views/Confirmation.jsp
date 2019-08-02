<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
table
{

margin-top:5%;
}

</style>
 


</head>
<body>
<form:form action="/Project/ConfirmSuccess" method="POST" >
<div class="ConfirmPage">

<h3 align="center">Payment is succesfully done.</h3>
<table border="1" align="center" width="30%">
<tr>
<td><b>Booking Number:</b></td>
<td>bookingId</td>
</tr>


<tr>
<td><b>Email Id:</b></td>
<td>${userEmailId}</td>
</tr>


<tr>
<td><b>Phone Number:</b></td>
<td>${userPhoneNum}</td>
</tr>

<tr>
<td><b>Check In:</b></td>
<td>checkInDate</td>
</tr>


<tr>
<td><b>Check Out:</b></td>
<td>checkOutDate</td>
</tr>

<tr>
<td><b>Hotel Name:</b></td>
<td>hotelName</td>
</tr>


<tr>
<td><b>Full Name:</b></td>
<td>${userFullName}</td>
</tr>


<tr>
<td><b>Total Price:</b></td>
<td>totalPrice</td>
</tr>
</table>
</div>
</form:form>
</body>
</html>
                        










