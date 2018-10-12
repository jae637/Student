<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.NoticeDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("euc-kr"); %>

<jsp:useBean id = "notice" scope = "page" class = "util.NoticeDataBean">
	<jsp:setProperty name = "notice" property = "*" />
</jsp:useBean>

<%
	notice.setReg_date(new Timestamp(System.currentTimeMillis()));
	
	NoticeDBBean dbPro = NoticeDBBean.getInstance();
	dbPro.insertNotice(notice);
	response.sendRedirect("n_list.jsp");
%>
