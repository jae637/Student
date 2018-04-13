<%@page
	import="org.apache.tomcat.dbcp.dbcp.DriverManagerConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>

<%!// ISO-8859-1 문자열을 Unicode 문자열로 바꾸는 메서드
	private String toUnicode(String str) {
		try {
			byte[] b = str.getBytes("ISO-8859-1");
			return new String(b);
		} catch (java.io.UnsupportedEncodingException uee) {
			System.out.println(uee.getMessage());
			return null;
		}
	}%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Connection conn = null;
	Statement stmt = null;
	String id = request.getParameter("ID"); //ID
	String secret = request.getParameter("SECRET"); // 비밀번호
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "choi1031");
		if (conn == null)
			throw new Exception("데이터베이스에 연결할 수 없습니다 <BR>");
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from signinfo where id = '" + id + "';");
		if (rs.next()) {
			String id2 = rs.getString("id");
			String password = rs.getString("password");
			String address = rs.getString("address");
			String email = rs.getString("email");
			String domain = rs.getString("domain");
			String number = rs.getString("number");
			String hobby = rs.getString("hobby");
		
			request.setAttribute("ID", toUnicode(id));
			request.setAttribute("PASSWORD", toUnicode(secret));
			request.setAttribute("ADDRESS", toUnicode(address));
			request.setAttribute("EMAIL", toUnicode(email));
			request.setAttribute("DOMAIN", toUnicode(domain));
			request.setAttribute("NUMBER", toUnicode(number));
			request.setAttribute("HOBBY", toUnicode(hobby));
		}
	} finally {
		try {
			stmt.close();
		} catch (Exception ignored) {
		}
		try {
			conn.close();
		} catch (Exception ignored) {
		}
	}
	RequestDispatcher dispatcher = request.getRequestDispatcher("SignInfo.jsp");
	dispatcher.forward(request, response);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>