<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	�Է� ���ڿ�  : ${param.STR1},${param.STR2 }
	<br>
	<br>
	�� ���ڿ��� �����ϱ�? ${param.STR1==param.STR2 }
	<br>
	��� ���ڿ��� ���� �Դϱ�? ${param.STR1<param.STR2 ? param.STR1 : param.STR2}
	
</body>
</html>