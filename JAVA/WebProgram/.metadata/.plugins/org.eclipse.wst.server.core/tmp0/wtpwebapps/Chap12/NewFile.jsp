<%@page
	import="org.apache.tomcat.dbcp.dbcp.DriverManagerConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>

<%!
    // ISO-8859-1 문자열을 Unicode 문자열로 바꾸는 메서드
    private String toUnicode(String str) { 
        try {
            byte[] b = str.getBytes( "ISO-8859-1");
            return new String(b);
        }
        catch (java.io.UnsupportedEncodingException uee) {
            System.out.println(uee.getMessage());
            return null;
        }
    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String code = request.getParameter("code");
	Connection conn = null;
	Statement stmt = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "choi1031");
		if (conn == null)
			throw new Exception("데이터베이스에 연결할 수 없습니다 <BR>");
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from goodsinfo where code = '" + code + "';");
		if (rs.next()) {
			String title = rs.getString("title");
			String writer = rs.getString("writer");
			int price = rs.getInt("price");
			request.setAttribute("CODE", code);
			request.setAttribute("TITLE", toUnicode(title));
			request.setAttribute("WRITER", toUnicode(writer));
			request.setAttribute("PRICE", new Integer(price));
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
	RequestDispatcher dispatcher = request.getRequestDispatcher("GoodsInfoViewer.jsp");
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