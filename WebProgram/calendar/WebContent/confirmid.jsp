<%@ page import="util.DBUtil"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID중복확인</title>
</head>
<body>
<%request.setCharacterEncoding("utf-8");%>
<% 
	String id = request.getParameter("id");
	DBUtil dbutil = DBUtil.getInstance();
	int check = dbutil.confirmId(id);
	
	if(check==1){%>
	<b><font color="red"><%=id%></font>는 이미 사용중인 아이디입니다.</b>
	<form name="checkForm" method="post" action="confirmid.jsp">
	<b>다른 아이디를 선택하세요.</b><br /><br />
	<input type="text" name="id"/>
	<input type="submit" value="ID중복확인"/>
	</form>
	<%
	}else{
	%><center>
	<b>입력하신<font color="red"><%=id%></font>는<br />
				사용하실 수 있는 ID입니다.</b><br /><br />
	<input type="button" value="닫기" onclick="setid()"></center>
	<%}%>
<script language="javascript">
	
	function setid(){
		opener.document.insertFrm.id.value="<%=id%>";
		self.close();
	}
</script>	


</body>
</html>