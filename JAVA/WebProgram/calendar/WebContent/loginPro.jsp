<%@ page import="util.DBUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 에러</title>
</head>
<body>

<% request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	DBUtil dbutil = DBUtil.getInstance();
	int check = dbutil.userCheck(id, password);
	
	if(check==1){
		session.setAttribute("sessionID", id);
		session.setAttribute("password", password);
		response.sendRedirect("loginAft.jsp");
	}
	else if (check==0){%>	
	<script>
	alert('비밀번호가 맞지 않습니다.');
	history.go(-1);
	</script>
	 	
  <% } else if(check==-1) { %>	
	<script>
	alert('해당 아이디가 존재하지 않습니다.');
	history.go(-1);
	</script>

<% } else  { %>  
	<script>
	alert('아이디가 맞지 않습니다.');
	history.go(-1); 
	</script>	
<% } %>
	
</body>
</html>