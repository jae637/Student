<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int sum =0;
	for(int cnt=1;cnt<=1000;cnt++)
		sum+=cnt;
	pageContext.setAttribute("RESULT", new Integer(sum));
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	1부터 1000까지 더한결과는? ${RESULT}
</body>
</html>