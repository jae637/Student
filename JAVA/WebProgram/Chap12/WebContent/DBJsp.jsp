<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>데이터베이스로 연결하기</title>
</head>
<body>
	<h3>데이터베이스 연결 테스트</h3>
	<%
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","choi1031");
		if(conn!=null){
			out.println("webdb 데이터베이스로 연결했습니다<BR>");
			conn.close();
			out.println("webdb 데이터베이스로의 연결을 끊었습니다<BR>");
		}
		else{
			out.println("webdb 데이터베이스로 연결할 수 없습니다<BR>");
		}
	%>
</body>
</html>