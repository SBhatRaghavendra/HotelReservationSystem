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
	function onRowClick() {
		var table = document.getElementById("hoteltable");
		var rows = table.getElementsByTagName("tr");
		for (i = 0; i < rows.length; i++) {
			var currentRow = table.rows[i];
			var createClickHandler = function(row) {
				return function() {
					document.getElementById("hName").value = row
							.getElementsByTagName("td")[0].innerHTML;
					document.getElementById("roomPrice").value = row
							.getElementsByTagName("td")[2].innerHTML;
					document.getElementById("hotelId").value = row
							.getElementsByTagName("td")[3].innerHTML;

					showAvailabilityBtn();
				};
			};
			currentRow.onclick = createClickHandler(currentRow);
		}

	}

	function showAvailabilityBtn() {
		var x = document.getElementById("btn1");
		x.style.display = "block";

	}

	function showBookingBtn() {
		var x = document.getElementById("btn2");
		x.style.display = "block";

	}

	function hideButton() {
		var x = document.getElementById("btn1");

		x.style.display = "none";
	}
</script>

</head>
<body class="backGround">
	<h2 class="headings">Hotel Reservation System</h2>
	<c:choose>
		<c:when test="${available}">
			<form action="payment" method="get">
				<h3 class="headings">Rooms are available!</h3>
				<input type="submit" value="Book Now" name="action"
					class="submit-btn" id="btn2" />
			</form>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${notAvailable}">
					<p id="error">Rooms are not available in the selected hotel.
						Please check out other hotels!</p>
				</c:when>
			</c:choose>
			<form action="checkAvailabilty" method="post" id="adminForm">
				<table id="hotelListTop">
					<tr>
						<td>Hotel Name:</td>
						<td><input id="hName" name="hotelName" readonly="readonly" /></td>

					</tr>

					<tr>
						<td>Room Price::</td>
						<td><input id="roomPrice" name="roomPrice"
							readonly="readonly" /></td>
					</tr>

					<tr>
						<td><input id="hotelId" name="hotelId" readonly="readonly"
							hidden="true" /></td>
					</tr>

				</table>

				<input type="submit" name="action" id="btn1" class="submit-btn"	value="Check Availability" />


			</form>


			<div align="center">
				<table border="1" cellpadding="5" class="hotelList" id="hoteltable">
					<caption>
						<h3 class="headings">List of Available Hotels</h3>
					</caption>
					<tr>
						<th>Name</th>
						<th>Image</th>
						<th>Room Price</th>
					</tr>
					<c:forEach var="hotel" items="${hotelDetails}">
						<tr onclick="onRowClick()">
							<td><c:out value="${hotel.hotelName}" /></td>
							<td><img src=<c:url value="${hotel.hotelImgUrl}"/>
								class="img-dimensions" /></td>
							<td><c:out value="${hotel.roomPrice}" /></td>
							<td id="hiddenCol"><c:out value="${hotel.hotelId}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>