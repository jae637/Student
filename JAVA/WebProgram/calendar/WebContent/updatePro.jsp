<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.BoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("euc-kr"); %>

<jsp:useBean id = "article" scope = "page"
	class = "util.BoardDataBean">
	<jsp:setProperty name = "article" property = "*"/>
</jsp:useBean>

<%
	String pageNum = request.getParameter("pageNum");
	
	BoardDBBean dbPro = BoardDBBean.getInstance();
	
	int check = dbPro.updateArticle(article);
	
	if(check == 1)
	{
%>
<meta http-equiv = "Refresh" 
	content = "0;url=list.jsp?pageNum=<%= pageNum %>">
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