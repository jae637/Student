<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�����ͺ��̽��� �����ϱ�</title>
</head>
<body>
	<h3>�����ͺ��̽� ���� �׽�Ʈ</h3>
	<%
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","choi1031");
		if(conn!=null){
			out.println("webdb �����ͺ��̽��� �����߽��ϴ�<BR>");
			conn.close();
			out.println("webdb �����ͺ��̽����� ������ �������ϴ�<BR>");
		}
		else{
			out.println("webdb �����ͺ��̽��� ������ �� �����ϴ�<BR>");
		}
	%>
</body>
</html>