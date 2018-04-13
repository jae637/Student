<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		ArrayList<String> item = new ArrayList<String>();
		item.add("딸기");
		item.add("오렌지");
		item.add("복숭아");
		request.setAttribute("FRUITS", item);
		RequestDispatcher dispatcher = request.getRequestDispatcher("FruitsView.jsp");
		dispatcher.forward(request, response);
	%>

</body>
</html>