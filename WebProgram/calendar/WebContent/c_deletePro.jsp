<><%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.BoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("euc-kr"); %>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	String password = request.getParameter("password");
	BoardDBBean dbPro = BoardDBBean.getInstance();
	int check = dbPro.deleteArticle(num, password);
	
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
		alert("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
		history.go(-1);
	-->
</script>
<%
	}
%>