<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.io.*,java.util.*,java.io.IOException" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시판 글쓰기 - 결과화면</title>
</head>
<body>
	<h2>글쓰기</h2>
	<% 
	request.setCharacterEncoding("euc-kr");
	String name = request.getParameter("NAME");
	String num1 = request.getParameter("NUMBER");
	String num2 = request.getParameter("NUMBER2");
	String id = request.getParameter("ID");
	String secret = request.getParameter("SECRET");
	String secretcur = request.getParameter("SECRETCUR");
	String email = request.getParameter("EMAIL");
	String domain = request.getParameter("DOMAIN");
	String first = request.getParameter("FIRST");
	String second = request.getParameter("SECOND");
	String trird = request.getParameter("TRIRD");
	String job = request.getParameter("JOB");
	String yn = request.getParameter("YESORNO");
	String hobby = request.getParameter("HOBBY");
	
	int int_num1= Integer.parseInt(num1);
	int int_num2= Integer.parseInt(num2);
	int Pfirst=Integer.parseInt(first);
	int Psecond=Integer.parseInt(second);
	int Ptrird=Integer.parseInt(trird);
	int yns= Integer.parseInt(yn);
	if (yns==1)
		yn="수신";
	else
		yn="거부";
	
	String result;
	PrintWriter writer = null;
	if(secret.equals(secretcur)){
		try{
			writer = response.getWriter();
			String filePath = application.getRealPath("/start2/result.txt");
			writer.printf("이름: %s \n", name);
			writer.printf("주민등록번호: %d-%d\n", int_num1,int_num2);
			writer.printf("아이디: %s\n", id);
			writer.printf("비밀번호: %s\n", secret);
			writer.printf("이메일: %s@%s\n", email,domain);
			writer.printf("핸드폰번호: %d-%d-%d\n", Pfirst,Psecond,Ptrird);
			writer.printf("직업: %s", job);
			writer.printf("수신여부: %s", yn);
			writer.printf("관심분야: %s", hobby);
			result = "SUCCESS";
		}
		catch (IOException filePath) {
			//result = "SUCCESS";
			result = "FAIL";
		}
		finally{
			try{
				writer.close();
			}
			catch (Exception e){
			}
		}
	}
	else{
		result = "FAIL";
	}
	response.sendRedirect("result.jsp?RESULT="+result);
	%>
	
</body>
</html>