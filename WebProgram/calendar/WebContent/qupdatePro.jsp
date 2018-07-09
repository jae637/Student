<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.BoardqDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("euc-kr"); %>

<jsp:useBean id = "article" scope = "page"
	class = "util.BoardqDataBean">
	<jsp:setProperty name = "article" property = "*"/>
</jsp:useBean>

<%
	String pageNum = request.getParameter("pageNum");
	
	BoardqDBBean dbPro = BoardqDBBean.getInstance();
	
	int check = dbPro.updateArticle(article);
	
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