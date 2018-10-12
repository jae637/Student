<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.BoardqDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("euc-kr"); %>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	String password = request.getParameter("password");
	BoardqDBBean dbPro = BoardqDBBean.getInstance();
	int check = dbPro.deleteArticle(num, password);
	
	if(check == 1)
	{
%>
<meta http-equiv = "Refresh" 
	content = "0;url=qlist.jsp?pageNum=<%= pageNum %>">
<%
	}
	else
	{
%>
<script language = "javascript">
	<!--
		alert("비밀번호가 틀렸습니다.");
		history.go(-1);
	-->
</script>
<%
	}
%>