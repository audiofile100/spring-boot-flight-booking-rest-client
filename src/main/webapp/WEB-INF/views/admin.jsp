<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<title>Admin</title>


<style>
.error {
	color: red;
	font-style: italic;
	font-weight: bold;
	font-size: small;
}
</style>
<script>
	$(document).ready(function() {
		$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
			localStorage.setItem('activeTab', $(e.target).attr('href'));
		});
		var activeTab = localStorage.getItem('activeTab');
		if (activeTab) {
			$('#myTab a[href="' + activeTab + '"]').tab('show');
		}
	});
</script>
</head>
<body>

	<div class="container">
		<br>
		<h3>Welcome ${pageContext.request.userPrincipal.name}</h3>
		<h5>
			<a href="${pageContext.request.contextPath}/home"
				class="btn btn btn-primary">Home</a>&nbsp;&nbsp;<a
				href="login?logout" class="btn btn-primary">Logout</a>
		</h5>
		<br>
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist" id="myTab">
			<li class="nav-item"><a class="nav-link active"
				data-toggle="tab" href="#flights">Flights</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#customers">Customers</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">

			<div id="flights" class="container tab-pane active">
				<br> <br>
				<h3>Flights</h3>
				<br>
				<frm:form action="saveFlight" method="POST" modelAttribute="flight">
					<table>
						<tr>
							<td>Flight Id:</td>
							<td><frm:input path="flightId" /></td>
							<td><frm:errors path="flightId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Flight Number:</td>
							<td><frm:input path="flightNumber" /></td>
							<td><frm:errors path="flightNumber" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Airline Id:</td>
							<td><frm:input path="airline.airlineId" /></td>
							<td><frm:errors path="airline.airlineId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Departure Airport Id:</td>
							<td><frm:input path="departFrom.airportId" /></td>
							<td><frm:errors path="departFrom.airportId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Arrival Airport Id:</td>
							<td><frm:input path="arriveAt.airportId" /></td>
							<td><frm:errors path="arriveAt.airportId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Departure Date Time:</td>
							<td><frm:input path="departureDateTime"
									placeHolder="yyyy-MM-dd HH:mm:ss" /></td>
							<td><frm:errors path="departureDateTime" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Arrival Date Time:</td>
							<td><frm:input path="arrivalDateTime"
									placeHolder="yyyy-MM-dd HH:mm:ss" /></td>
							<td><frm:errors path="arrivalDateTime" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Price:</td>
							<td><frm:input path="price" /></td>
							<td><frm:errors path="price" cssClass="error" /></td>
						</tr>
						<tr>
							<td align="right" colspan="2"><input
								class="btn btn-primary btn-lg" type="submit" value="submit" /></td>
						</tr>
					</table>
				</frm:form>
				<br> <br>
			</div>

			<div id="customers" class="container tab-pane fade">
				<br> <br>
				<h3>Customers</h3>
				<br>
				<frm:form action="saveCustomer" method="POST"
					modelAttribute="customer">
					<table>
						<tr>
							<td>Customer Id:</td>
							<td><frm:input path="customerId" /></td>
							<td><frm:errors path="customerId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Name:</td>
							<td><frm:input path="name" /></td>
							<td><frm:errors path="name" cssClass="error" /></td>
						</tr>
						<tr>
							<td>User Id:</td>
							<td><frm:input path="user.userId" /></td>
							<td><frm:errors path="user.userId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Address Line 1:</td>
							<td><frm:input path="customerAddress.addressLine1" /></td>
							<td><frm:errors path="customerAddress.addressLine1"
									cssClass="error" /></td>
						</tr>
						<tr>
							<td>Address Line 2:</td>
							<td><frm:input path="customerAddress.addressLine2" /></td>
							<td><frm:errors path="customerAddress.addressLine2"
									cssClass="error" /></td>
						</tr>
						<tr>
							<td>City:</td>
							<td><frm:input path="customerAddress.city" /></td>
							<td><frm:errors path="customerAddress.city" cssClass="error" /></td>
						</tr>
						<tr>
							<td>State:</td>
							<td><frm:input path="customerAddress.state" /></td>
							<td><frm:errors path="customerAddress.state"
									cssClass="error" /></td>
						</tr>
						<tr>
							<td>Phone:</td>
							<td><frm:input path="phone" /></td>
							<td><frm:errors path="phone" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><frm:input path="email" /></td>
							<td><frm:errors path="email" cssClass="error" /></td>
						</tr>

						<tr>
							<td>SSN:</td>
							<td><frm:input path="ssn" /></td>
							<td><frm:errors path="ssn" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Gender:</td>
							<td><frm:input path="gender" /></td>
							<td><frm:errors path="gender" cssClass="error" /></td>
						</tr>
						<tr>
							<td>DOB:</td>
							<td><frm:input type="date" path="dob" /></td>
							<td><frm:errors path="dob" cssClass="error" /></td>
						</tr>
						<tr>
							<td colspan="2" align="right"><input
								class="btn btn-primary btn-lg" type="submit" value="submit" /></td>
						</tr>
					</table>
				</frm:form>
				<br> <br>
			</div>
		</div>

	</div>

</body>
</html>