<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "util.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
<%
	String id = request.getParameter("id");
	String question = request.getParameter("question");
	String answer = request.getParameter("answer");
	
	DBUtil dbu = DBUtil.getInstance();
	DBUtil dbu2 = DBUtil.getInstance();
	int check = dbu.findPassword(id,question,answer);
	if(check==1){
		SignupDTO member1 = dbu2.getUserInfo(id);
		out.println("<script>alert('비밀번호는 " + member1.getPassword() + " 입니다.');document.location.href='login.jsp';</script>");
	}else {
%>
	<script>
	alert("입력하신 정보를 다시 확인해주세요.");
	history.go(-1);
	</script>
<%}%>
</body>
</html>