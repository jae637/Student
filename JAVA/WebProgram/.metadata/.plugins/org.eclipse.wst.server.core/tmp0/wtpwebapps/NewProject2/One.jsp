<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String name = request.getParameter("NAME");
	application.setAttribute("NAME", name);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�� ���ø����̼� ������ �����ϱ�</title>
</head>
<body>
	NAME �����Ͱ� ����Ǿ����ϴ�.
</body>
</html>