<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���ø����� �λ��ϱ�</title>
</head>
<body>
	<jsp:plugin type="applet" code="GreetingApplet.class" codebase="class" width="500"
		heigth="200">
		<jsp:param name="GREETING" value="��ü�� ���⸸���Ͻÿɴϱ�." />
		<jsp:params name="FONT" value="�ü�ü" />
	</jsp:plugin>
</body>
</html>