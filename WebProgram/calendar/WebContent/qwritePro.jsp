<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.BoardqDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("euc-kr"); %>

<jsp:useBean id = "article" scope = "page" class = "util.BoardqDataBean">
	<jsp:setProperty name = "article" property = "*" />
</jsp:useBean>

<%
	article.setReg_date(new Timestamp(System.currentTimeMillis()));
	
	BoardqDBBean dbPro = BoardqDBBean.getInstance();
	dbPro.insertArticle(article);
	response.sendRedirect("qlist.jsp");
%>
