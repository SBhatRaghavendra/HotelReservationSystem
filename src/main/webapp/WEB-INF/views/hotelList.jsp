<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hotel List</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script>
	localStorage.clear();
</script>

</head>
<body class="backGround">
	<h2  class="headings">Hotel Reservation System</h2>
	<div align="center">
		<table border="1" cellpadding="5" class="hotelList">
			<caption>
				<h3 class="headings">List of Available Hotels</h3>
			</caption>
			<tr>
				<th>Name</th>
				<th>Image</th>
				<th>Room Price</th>
			</tr>
			<c:forEach var="hotel" items="${hotelDetails}">
				<tr>
					<td><c:out value="${hotel.hotelName}" /></td>
					<td><img src=<c:url value="${hotel.hotelImgUrl}"/>
						class="img-dimensions" /></td>
					<td>
						<div>
							<c:out value="${hotel.roomPrice}" />
						</div>

						<div>
							<input type="button" class="btn btn-warning" value="Check Availability" />
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>