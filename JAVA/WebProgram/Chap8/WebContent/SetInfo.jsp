<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<jsp:useBean class="mall.PersonalInfo" id="pinfo" scope="request">
	<jsp:setProperty property="name" name="pinfo" value="김현수" />
	<jsp:setProperty property="gender" name="pinfo" value="남" />
	<jsp:setProperty property="age" name="pinfo" value="23" />
	<jsp:forward page="CustomerInfoViewer.jsp"></jsp:forward>
</jsp:useBean>