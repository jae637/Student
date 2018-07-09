<%@ page import="util.DBUtil"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 완료</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String question = request.getParameter("question");
	String answer = request.getParameter("answer");
	String name = request.getParameter("name");
	String residentnum = request.getParameter("number1")+"-"+request.getParameter("number2");
	String address = request.getParameter("address");
	String phone = request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
	String email = request.getParameter("email1")+"@"+request.getParameter("email2");
			
	
	Connection conn = DBUtil.getMySQLConnection();
	
	String sql = "insert into signup(id, password, question, answer, name, residentnum, address, phone, email) values(?,?,?,?,?,?,?,?,?)";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	
	pstmt.setString(1, id);
	pstmt.setString(2, password);
	pstmt.setString(3, question);
	pstmt.setString(4, answer);
	pstmt.setString(5, name);
	pstmt.setString(6, residentnum);
	pstmt.setString(7, address);
	pstmt.setString(8, phone);
	pstmt.setString(9, email);
	
	pstmt.executeUpdate();
	
	DBUtil.close(pstmt);
	DBUtil.close(conn);
	
%>
<script>
alert("회원가입 되었습니다.");
window.location.href="login.jsp";
</script>

</body>
</html>