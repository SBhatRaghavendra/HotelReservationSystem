<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hotel Reservation System</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="" />
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/css?family=|Roboto+Sans:400,700|Playfair+Display:400,700">

<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/animate.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/owl.carousel.min.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/aos.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-datepicker.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery.timepicker.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/fancybox.min.css" />">

<link rel="stylesheet" href="../fonts/ionicons/css/ionicons.min.css">
<link rel="stylesheet"
	href="/resources/fonts/fontawesome/css/font-awesome.min.css">

<!-- Theme Style -->
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">

</head>
<body>
	<header class="site-header js-site-header">
		<div class="container-fluid">
			<div class="row align-items-center">
				<div class="col-6 col-lg-4 site-logo" data-aos="fade">
					<a href="index.html">HVNR</a>
				</div>
				<div class="col-6 col-lg-8">


					<div class="site-menu-toggle js-site-menu-toggle" data-aos="fade">
						<span></span> <span></span> <span></span>
					</div>
					<!-- END menu-toggle -->

					<div class="site-navbar js-site-navbar">
						<nav role="navigation">
							<div class="container">
								<div class="row full-height align-items-center">
									<div class="col-md-6 mx-auto">
										<ul class="list-unstyled menu">
											<li class="active"><a href="home.jsp">Home</a></li>
											<li><a href="login.jsp">Login</a></li>
											<li><a href="register.jsp">Register</a></li>
										</ul>
									</div>
								</div>
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- END head -->

	<section class="site-hero overlay" data-stellar-background-ratio="0.5">
		<div class="container">
			<div
				class="row site-hero-inner justify-content-center align-items-center">
				<div class="col-md-10 text-center" data-aos="fade-up">
					<span
						class="custom-caption text-uppercase text-white d-block  mb-3">Welcome
						To Hotel Reservation </span>
					<h1 class="heading">Find A Best Place To Stay</h1>
				</div>
			</div>
		</div>

	</section>
	<!-- END section -->

	<section class="section bg-light pb-0">
		<div class="container">

			<div class="row check-availabilty" id="next">
				<div class="block-32" data-aos="fade-up" data-aos-offset="-200">

					<form method="POST" action="homeEntryValidation">
						<div class="row">
							<div class="col-md-2 mb-2 mb-md-0">
								<label for="location" class="font-weight-bold text-black">Location</label>
								<div class="field-icon-wrap">
									<div class="icon">
										<span class="ion-ios-arrow-down"></span>
									</div>
									<select name="location" class="form-control" required>
										<option value="">Select</option>
										<option value="1">Marathahalli</option>
										<option value="2">Silk Board</option>
									</select>
								</div>
							</div>

							<div class="col-md-2 mb-2 mb-md-0">
								<label for="checkin_date"
									class="font-weight-bold text-black date">Check In</label>
								<div class="field-icon-wrap">
									<div class="icon">
										<span class="icon-calendar"></span>
									</div>
									<input name="checkInDate" class="form-control date" required/>
								</div>
							</div>

							<div class="col-md-2 mb-2 mb-md-0">
								<label for="checkout_date" class="font-weight-bold text-black">Check
									Out</label>
								<div class="field-icon-wrap">
									<div class="icon">
										<span class="icon-calendar"></span>
									</div>
									<input name="checkOutDate" class="form-control date" required/>
								</div>
							</div>

							<div class="col-md-6 mb-2 mb-md-0">
								<div class="row">
									<div class="col-md-6 mb-3 mb-md-0">
										<label for="roomType" class="font-weight-bold text-black">Room
											Type</label>
										<div class="field-icon-wrap">
											<div class="icon">
												<span class="ion-ios-arrow-down"></span>
											</div>
											<select name="roomType" class="form-control" required>
												<option value="">Select</option>
												<option value="1">Single Bedroom</option>
												<option value="2">Double Bedroom</option>
											</select>
										</div>
									</div>

									<div class="col-md-6 mb-3 mb-md-0">
										<label for="noOfRooms" class="font-weight-bold text-black">No
											of rooms</label>
										<div class="field-icon-wrap">
											<input type="number" name="noOfRooms" min=1 max=5 class="form-control" required/>
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-6 mt-2 col-lg-3 align-self-end">
								<input type="submit" value="Check Availabilty"
									class="btn btn-primary btn-block text-white" />
							</div>
						</div>
					</form>
				</div>


			</div>
		</div>
	</section>



	<script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
	<script
		src="<c:url value="/resources/js/jquery-migrate-3.0.1.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/owl.carousel.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.stellar.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.fancybox.min.js" />"></script>


	<script src="<c:url value="/resources/js/aos.js" />"></script>

	<script src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.timepicker.min.js" />"></script>



	<script src="<c:url value="/resources/js/main.js" />"></script>
</body>
</html>
</body>
</html>