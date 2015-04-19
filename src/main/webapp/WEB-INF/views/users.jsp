<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User report</title>
</head>
<body>
	<center>
		<table border=1 >
				<tr>
					<th>Account</th>
					<th>Email</th>
					<th>Edition</th>
				</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.accountIdentifier}</td>
					<td>${user.email}</td>
					<td>${user.editionCode}</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>