<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment page</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">

<style type="text/css">
tr {
	border-color: black;
	border-width: 3px;
}

td {
	font-size: 28px;
	color: white;
}

.labels {
	text-align: center;
}

.box {
	width: 800px;
	position: absolute;
	top: 30%;
	left: 55%;
	transform: translate(-50%, -50%);
}
</style>
</head>
<body class="backGround">
	<form action="paymentSuccess" method="POST">
		<h1 align="center" class="headings">Welcome To Payment Page</h1>

		<table width="100%" cellpadding="2">


			<tr>
				<td class="labels">Hotel Name</td>
				<td>${hotelName}</td>
			</tr>

			<tr>
				<td class="labels">Price</td>
				<td>${roomPrice}</td>
			</tr>

			<tr>
				<td class="labels">Payment</td>
				<td><select class="select-dropdown" required="required">
						<option value="">Select</option>
						<option value="Credit Card">Credit Card</option>
						<option value="Debit Card">Debit Card</option>
				</select></td>
			</tr>

		</table>
		<input type="text" name="" value="${roomId}" readonly="readonly" hidden="true"/>
		<br> <input type="submit" class = "submit-btn" value="Pay Now" />


	</form>
</body>
</html>
