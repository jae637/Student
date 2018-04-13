<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	입력 문자열  : ${param.STR1},${param.STR2 }
	<br>
	<br>
	두 문자열이 같습니까? ${param.STR1==param.STR2 }
	<br>
	어느 문자열이 먼저 입니까? ${param.STR1<param.STR2 ? param.STR1 : param.STR2}
	
</body>
</html>