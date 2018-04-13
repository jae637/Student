<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.*,java.util.*,java.io.IOException"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시판 글쓰기 - 결과화면</title>
</head>
<body>
	<h2>글쓰기</h2>
	<%
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("ID"); //ID
		String secret = request.getParameter("SECRET"); // 비밀번호
		String secretcur = request.getParameter("SECRETCUR");
		String email = request.getParameter("EMAIL"); //이메일
		String domain = request.getParameter("DOMAIN");
		String first = request.getParameter("FIRST"); //전화번호
		String second = request.getParameter("SECOND");
		String trird = request.getParameter("TRIRD");
		String address = request.getParameter("ADDRESS"); //주소
		String hobby = request.getParameter("HOBBY"); //취미

		String phonenumber=first+second+trird;
		int Pfirst = Integer.parseInt(first);
		int Psecond = Integer.parseInt(second);
		int Ptrird = Integer.parseInt(trird);

		if (secret.equals(secretcur))
		{}
		else
			throw new Exception("비밀번호가 다릅니다.");
		if (id == null || secret == null || email == null || second == null || address == null || hobby == null)
			throw new Exception("데이터를 입력하세요.");
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "choi1031");
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			String command = String.format("insert into signinfo (id, password, address, email,domain, number, hobby) values ( '%s','%s','%s','%s','%s','%s','%s'); ",
					id, secret, address, email, domain, phonenumber,hobby);
			int rowNum = stmt.executeUpdate(command);
			if (rowNum < 1)
				throw new Exception("데이터를 DB에 입력할 수 없습니다.");
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
		response.sendRedirect("SubscriptionResult.jsp");
	%>

</body>
</html>