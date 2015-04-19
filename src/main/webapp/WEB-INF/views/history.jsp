<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event log</title>
</head>
<body>
	<center>
		<table border=1>
			<tr>
				<th>Timestamp</th>
				<th>Event Type</th>
				<!-- <th>Account</th> -->
				<th>Email address</th>
			</tr>
			<c:forEach items="${log}" var="event">
				<tr>
					<td>${event.timestamp}</td>
					<td>${event.type}</td>
				<!-- 	<td>${event.accountIdentifier}</td> -->
					<td>${event.email}</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>