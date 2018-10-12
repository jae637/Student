<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.NoticeDBBean" %>
<%@ page import = "util.NoticeDataBean" %>
<%@ include file = "view/color.jsp" %>

<html>
<head><title>게시판</title></head>
<link href = "style.css" rel = "stylesheet" type ="text/css">
<script language = "javascript" src = "script.js"></script>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	
	try
	{
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		NoticeDataBean notice = dbPro.updateGetNotice(num);
%>

<body bgcolor = "<%= bodyback_c %>">
<center><b>글 수정</b></center><br>
<form method = "post" name = "n_writeform" action = "n_updatePro.jsp?pageNum=<%= pageNum %>"
	onSubmit = "return wrtieSave()">
<table width = "400" border = "1" cellspacing = "0" cellpadding = "0"
	bgcolor = "<%= bodyback_c %>" align = "center">
<tr>
	<td width = "70" bgcolor = "<%= value_c %>"	align = "center">이 름</td>
	<td align = "left" width = "330">
		<input type = "text" size = "10" maxlength = "10" name = "writer"
			value = "<%= notice.getWriter() %>">
		<input type = "hidden" name = "num" value = "<%= notice.getNum() %>">
	</td>
</tr>
<tr>
	<td width = "70" bgcolor = "<%= value_c %>" align = "center">제 목</td>
	<td align = "left" width = "330">
		<input type = "text" size = "40" maxlength = "50" name = "subject"
			value = "<%= notice.getSubject() %>">
	</td>
</tr>

<tr>
	<td width = "70" bgcolor = "<%= value_c %>" align = "center">내 용</td>
	<td align = "left" width = "310">
		<textarea name = "content" 
			rows = "13" cols = "40"><%= notice.getContent() %>
		</textarea>
	</td>
</tr>
<tr>
	<td width = "70" bgcolor = "<%= value_c %>" align = "center">비밀번호</td>
	<td align = "left" width = "330">
		<input type = "password" size = "8" maxlength = "12" name = "password">
	</td>
</tr>
<tr>
	<td colspan = "2" bgcolor = "<%= value_c %>" align = "center">
		<input type = "submit" value = "글 수정">
		<input type = "reset" value = "다시 작성">
		<input type = "button" value = "목록보기"
			onclick = "document.location.href='n_list.jsp?pageNum=<%= pageNum %>'">
	</td>
</tr>
</table>
</form>

<%
	}catch(Exception e) {}
%>
</body>
</html>