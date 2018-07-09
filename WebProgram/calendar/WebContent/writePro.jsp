<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.BoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("euc-kr"); %>

<jsp:useBean id = "article" scope = "page" class = "util.BoardDataBean">
	<jsp:setProperty name = "article" property = "*" />
</jsp:useBean>

<%
	article.setReg_date(new Timestamp(System.currentTimeMillis()));
	
	BoardDBBean dbPro = BoardDBBean.getInstance();
	dbPro.insertArticle(article);
	response.sendRedirect("list.jsp");
%>
