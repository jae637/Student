<%@page import="util.DBUtil,util.SignupDTO "%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String id = session.getAttribute("sessionID").toString();
	request.setCharacterEncoding("utf-8");
	DBUtil dbu = DBUtil.getInstance();
	SignupDTO dbpoint = dbu.getPoint(id);
	SignupDTO member = dbu.getUserInfo(id);
	String email = member.getEmail1()+"@"+member.getEmail2();
	
	int dbPoint = Integer.parseInt(dbpoint.getPoint());
	String coupon = "coupon5000";
	if(dbPoint >= 5000){
  		dbu.minusPoint5000(id);
  		dbu.insertEvent(id, coupon, email);
  		out.println("<script>alert('신청되었습니다.');document.location.href='event.jsp';</script>");
	}else{ 
		out.println("<script>alert('포인트가 부족합니다.');document.location.href='event.jsp';</script>");
	}
	
%>
</body>
</html>