<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*"%>
<%@include file="/WEB-INF/views/include.jsp"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
<meta charset="ISO-8859-1">
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />
<title>Admin view Page</title>
<style type="text/css">
.error {
	color: red;
}
</style>

<style>
#sub-left {
	float: left;
	padding-left: 350px;
}

#sub-right {
	float: right;
	padding-left: 300px;
	position: relative;
	height: 100px;
	top: 20%;
	left: 50%;
	margin: -100px -100px -0 -150px;
	width: 50%;
}

.myTable {
	margin-top: 200px;
}

h3 {
	color: red;
}

.logout {
	margin: -50px 0px;
	float: right;
	color: white;
	height: 50x;
	width: 90px;
	background-color: green;
	padding: 10px;
	text-align: center;
}

table tr td {
	padding: 15px 15px;
}

tr {
	margin: 10px;
}
</style>

<script>
	function onRowClick() {
		var table = document.getElementById("hoteltable");
		var rows = table.getElementsByTagName("tr");
		for (i = 0; i < rows.length; i++) {
			var currentRow = table.rows[i];
			var createClickHandler = function(row) {
				return function() {
					document.getElementById("hId").value = row
							.getElementsByTagName("td")[0].innerHTML;
					document.getElementById("hName").value = row
							.getElementsByTagName("td")[1].innerHTML;
					document.getElementById("hArea").value = row
							.getElementsByTagName("td")[2].innerHTML;
					document.getElementById("hImgUrl").value = row
							.getElementsByTagName("td")[4].innerHTML;

				};
			};
			currentRow.onclick = createClickHandler(currentRow);
			hideButton();
			showDelete();
			showEdit();
			document.getElementById("SingleBedRoom").style.display = "none";
			document.getElementById("DoubleBedRoom").style.display = "none";
		}

	}

	function showDelete() {
		var x = document.getElementById("deleteButton");
		x.style.display = "block";

	}

	function showEdit() {
		var x = document.getElementById("editButton");
		x.style.display = "block";

	}

	function hideButton() {
		var x = document.getElementById("addButton");

		x.style.display = "none";
	}

	function resetfield() {
		document.getElementById("hId").value = 0;
		document.getElementById("hName").value = "";
		document.getElementById("hArea").value = "";
		document.getElementById("hImgUrl").value = "";
		document.getElementById("OneBeddedRoomNum").value = "";
		document.getElementById("TypeOneprice").value = "";
		document.getElementById("TwoBeddedRoomNum").value = "";
		document.getElementById("TypeTwoprice").value = "";
	}
	function showButton() {
		resetfield();
		document.getElementById("SingleBedRoom").style.display = "block";
		document.getElementById("DoubleBedRoom").style.display = "block";
		var x = document.getElementById("addButton");
		var y = document.getElementById("deleteButton");
		var z = document.getElementById("editButton");
		x.style.display = "inline";
		y.style.display = "none";
		z.style.display = "none";
	}
</script>
</head>
<body class="backGround">

	<%
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		if (session.getAttribute("loggedOut") != null) {
			 response.sendRedirect("home");
		}
	%>
	<h2 align="center">Welcome to Hotel Admin Page</h2>
	<a class="logout" href="logout">LogOut</a>
	<br>
	<div id="sub-left">
		<form:form action="hotelaction" method="post" modelAttribute="hotel">
			<table class="hotelDetails">
				<tr>
					<td><form:input id="hId" path="hotelId" hidden="true"></form:input></td>
				</tr>

				<tr>
					<td>Hotel Name:</td>
					<td><form:input id="hName" path="hotelName"></form:input></td>
					<td><form:errors path="hotelName" cssClass="error"></form:errors></td>

				</tr>

				<tr>
					<td>Hotel Area:</td>
					<td><form:select id="hArea" path="hotelArea">
							<option value="">~~Select~~</option>
							<form:option value="Marathahalli">Marathalli</form:option>
							<form:option value="Silk Board">SilkBoard</form:option>
						</form:select></td>
					<td><form:errors path="hotelArea" cssClass="error"></form:errors></td>

				</tr>

				<tr>
					<td>Hotel Image URL:</td>
					<td><form:input id="hImgUrl" path="hotelImgUrl"></form:input></td>
					<td><form:errors path="hotelImgUrl" cssClass="error"></form:errors></td>

				</tr>
			</table>

			<div id="sub-right">

				<input type="submit" value="Add" name="action" id="addButton" /> <br>
				<input type="submit" value="Edit" name="action" id="editButton"
					hidden /> <br> <input type="submit" value="Delete"
					name="action" id="deleteButton" hidden /><br> <input
					type="button" name="reset" value="Reset" onclick="showButton();" /><br>

			</div>

			<div id="SingleBedRoom">
				<table class="hotelDetails">
					<tr>
						<td>SingleBedRoom :</td>
						<td><input type="text" name="OneBeddedRoomNum"
							id="OneBeddedRoomNum" required display="" /></td>
					</tr>

					<tr>
						<td>Price :</td>
						<td><input type="text" name="TypeOneprice" id="TypeOneprice"
							required /></td>
					</tr>


				</table>
			</div>

			<div id="DoubleBedRoom">
				<table class="hotelDetails">
					<tr>
						<td>DoubleBedRoom:</td>
						<td><input type="text" name="TwoBeddedRoomNum"
							id="TwoBeddedRoomNum" required /></td>
					</tr>

					<tr>
						<td>Price :</td>
						<td><input type="text" name="TypeTwoprice" id="TypeTwoprice"
							required /></td>
					</tr>

				</table>
			</div>

		</form:form>
	</div>



	<div class="myTable">
		<br> <br> <br> <br> <br> <br> <br>

		<h2 align="center">Hotel Details</h2>
		<h3>To Edit Or Delete Operations Please Double Click On Table Row</h3>
		<table id="hoteltable" class="hotelDetails" border="1" align="center">
			<tr>
				<td><h4>Id</h4></td>
				<td><h4>Name</h4></td>
				<td><h4>Area</h4></td>
				<td><h4>Image</h4></td>
			</tr>
			<c:forEach var="hotels" items="${hotelList}">
				<tr onclick="onRowClick()">
					<td>${hotels.hotelId}</td>
					<td>${hotels.hotelName}</td>
					<td>${hotels.hotelArea}</td>
					<td><img src=<c:url value="${hotels.hotelImgUrl}"/>
						class="img-dimensions" /></td>
					<td hidden>${hotels.hotelImgUrl}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>