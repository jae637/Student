<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.NoticeDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("euc-kr"); %>

<jsp:useBean id = "notice" scope = "page"
	class = "util.NoticeDataBean">
	<jsp:setProperty name = "notice" property = "*"/>
</jsp:useBean>

<%
	String pageNum = request.getParameter("pageNum");
	
	NoticeDBBean dbPro = NoticeDBBean.getInstance();
	
	int check = dbPro.updateNotice(notice);
	
	if(check == 1)
	{
%>
<meta http-equiv = "Refresh" 
	content = "0;url=n_list.jsp?pageNum=<%= pageNum %>">
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