<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<%
	String arr[]={"불고기 백반","오므라이스","콩국수"};
	request.setAttribute("MENU", arr);
%>
<jsp:forward page="LunchMenuView.jsp"></jsp:forward>