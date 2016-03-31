<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOME</title>
</head>
<body>
<body>
	<h1>Welcome</h1>
	<h2>
		Hi
		<%=session.getAttribute("user.username")%>!
	</h2>
</body>
</html>