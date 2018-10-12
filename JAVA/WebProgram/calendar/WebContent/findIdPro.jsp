<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% request.setCharacterEncoding("utf-8");%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 찾기</title>
</head>
<body>
<%
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	
	DBUtil dbu = DBUtil.getInstance();
	DBUtil dbu2 = DBUtil.getInstance();
	int check = dbu.findId(name, phone);
	
	if(check==1){
		SignupDTO member1 = dbu2.getUserInfo2(name);
		out.println("<script>alert('아이디는 " + member1.getId() + " 입니다.');document.location.href='login.jsp';</script>");

%>
<%}else {%>
	<script>
	alert("이름 또는 비밀번호가 맞지 않습니다 .");
	history.go(-1);
	</script>
<%}%>	
</body>
</html>