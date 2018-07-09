<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.NoticeDBBean" %>
<%@ page import = "util.NoticeDataBean" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file = "view/color.jsp" %>
<html>
<head><title>공지사항</title></head>
<link href = "style.css" rel = "stylesheet" type = "text/css">


<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	try
	{
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		NoticeDataBean notice = dbPro.getNotice(num);
		
		int ref = notice.getRef();
		int re_step = notice.getRe_step();
		int re_level = notice.getRe_level();
%>

<body bgcolor = "<%= bodyback_c %>">
<center><b>글내용 보기</b><br>
<form>
<table width = "500" border = "1" cellspacing = "0" cellpadding = "0"
	bgcolor = "<%= bodyback_c %>" align = "center">
<tr height = "30">
	<td align = "center" width = "125" bgcolor = "<%= value_c %>">글 번호</td>
	<td align = "center" width = "125" align = "center">
		<%= notice.getNum() %>
	</td>
	<td align = "center" width = "125" bgcolor = "<%= value_c %>">조회수</td>
	<td align = "center" width = "125" align = "center">
		<%= notice.getReadcount() %>
	</td>
</tr>
<tr height = "30">
	<td align = "center" width = "125" bgcolor = "<%= value_c %>">작성자</td>
	<td align = "center" width = "125" align = "center">
		<%= notice.getWriter() %>
	</td>
	<td align = "center" width = "125" bgcolor = "<%= value_c %>">작성일</td>
	<td align = "center" width = "125" align = "center">
		<%= sdf.format(notice.getReg_date()) %>
	</td>
</tr>
<tr height = "30">
	<td align = "center" width = "125" bgcolor = "<%= value_c %>">글 제목</td>
	<td align = "center" width = "375" align = "center" colspan = "3">
		<%= notice.getSubject()%>
	</td>
</tr>
<tr>
	<td align = "center" width = "125" bgcolor = "<%= value_c %>">글 내용</td>
	<td align = "left" width = "375" colspan = "3">
		<pre><%= notice.getContent() %></pre>
	</td>
</tr>
<tr height = "30">
	<td colspan = "4" bgcolor = "<%= value_c %>" align = "right">
		
		<% if(session.getAttribute("sessionID")==null){ %>
		<input type = "button" value="글목록" 
		onclick = "document.location.href='n_list.jsp?pageNum=<%= pageNum %>'">
		
		<%}else{
			String id = (String)session.getAttribute("sessionID");
			if(id.equals("admin")){%>
		<input type = "button" value = "글 수정" 
			onclick = "document.location.href=
			'n_updateForm.jsp?num=<%= notice.getNum() %>&pageNum=<%= pageNum %>'">
			&nbsp;&nbsp;&nbsp;&nbsp;
		<input type = "button" value = "글 삭제" 
			onclick = "document.location.href=
			'n_deleteForm.jsp?num=<%= notice.getNum() %>&pageNum=<%= pageNum %>'">
			&nbsp;&nbsp;&nbsp;&nbsp;
		<input type = "button" value = "글 목록" 
			onclick = "document.location.href='n_list.jsp?pageNum=<%= pageNum %>'">
		<%}else{ %>	
		<input type = "button" value="글목록" 
		onclick = "document.location.href='n_list.jsp?pageNum=<%= pageNum %>'"><%} %>
		<%} %>

<%
	}catch(Exception e) {}
%>
</form>
</center>
