<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>

<%
	String str1= request.getParameter("NUM1");
	String str2 =request.getParameter("NUM2");
	int num1 = Integer.parseInt(str1);
	int num2 = Integer.parseInt(str2);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ ���α׷�</title>
</head>
<body>
	<c:catch var="e">
		<% int result = num1/num2; %>
		�������� �����? <%=result%>
	</c:catch>
	<c:if test="${e!=null}">
		���� �޽���: ${message}
	</c:if>
</body>
</html>