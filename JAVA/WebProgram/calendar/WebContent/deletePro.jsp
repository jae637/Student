<%@page import = "util.DBUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>탈퇴</title>
</head>
<body>
<%
	String id = (String)session.getAttribute("sessionID");
	String password = request.getParameter("password");
	
	DBUtil dbu = DBUtil.getInstance();
	int check = dbu.deleteMember(id, password);
	
	
	if(check==1){
		session.invalidate();	
%>
<center>
<form action="login.jsp" name="insertFrm" method="post">
<b><h2>회원정보가 삭제되었습니다.</h2></b>
<b><h2>이용해주셔서 감사합니다.</h2></b>

<input type="submit" value="확인">
</form>
	
<%}else {%>
	<script>
	alert("비밀번호가 맞지 않습니다 .");
	history.go(-1);
	</script>
<%}%>	
</center>
</body>
</html>