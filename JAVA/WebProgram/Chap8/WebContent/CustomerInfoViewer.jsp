<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ������</title>
</head>
<body>
	<jsp:useBean class="mall.PersonalInfo" id="pinfo" scope="request">
		�̸�:<jsp:getProperty property="name" name="pinfo" />
		<br>����:<jsp:getProperty property="gender" name="pinfo" />
		<br>����:<jsp:getProperty property="age" name="pinfo" />
	</jsp:useBean>
</body>
</html>