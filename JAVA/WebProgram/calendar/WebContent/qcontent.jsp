<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import ="util.BoardqDataBean" %>
<%@ page import = "util.BoardqDBBean" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file = "../view/color.jsp" %>

<html>
<head><title>게시판</title></head>
<link href = "style.css" rel = "stylesheet" type = "text/css">

<%
	String id = (String)session.getAttribute("sessionID");
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	try
	{
		BoardqDBBean dbPro = BoardqDBBean.getInstance();
		BoardqDataBean article = dbPro.getArticle(num);
		
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
%>

<body bgcolor = "<%= bodyback_c %>">
<center><b>글내용 보기</b><br>
<form>
<table width = "500" border = "1" cellspacing = "0" cellpadding = "0"
	bgcolor = "<%= bodyback_c %>" align = "center">
<tr height = "30">
	<td align = "center" width = "125" bgcolor = "<%= value_c %>">글 번호</td>
	<td align = "center" width = "125" align = "center">
		<%= article.getNum() %>
	</td>
	<td align = "center" width = "125" bgcolor = "<%= value_c %>">조회수</td>
	<td align = "center" width = "125" align = "center">
		<%= article.getReadcount() %>
	</td>
</tr>
<tr height = "30">
	<td align = "center" width = "125" bgcolor = "<%= value_c %>">작성자</td>
	<td align = "center" width = "125" align = "center">
		<%= article.getWriter() %>
	</td>
	<td align = "center" width = "125" bgcolor = "<%= value_c %>">작성일</td>
	<td align = "center" width = "125" align = "center">
		<%= sdf.format(article.getReg_date()) %>
	</td>
</tr>
<tr height = "30">
	<td align = "center" width = "125" bgcolor = "<%= value_c %>">글 제목</td>
	<td align = "center" width = "375" align = "center" colspan = "3">
		<%= article.getSubject() %>
	</td>
</tr>
<tr>
	<td align = "center" width = "125" bgcolor = "<%= value_c %>">글 내용</td>
	<td align = "left" width = "375" colspan = "3">
		<pre><%= article.getContent() %></pre>
	</td>
</tr>
<tr height = "30">
	<td colspan = "4" bgcolor = "<%= value_c %>" align = "right">
<%if(session.getAttribute("sessionID") == null ){ %>
	<input type = "button" value = "글 목록" 
			onclick = "document.location.href='qlist.jsp?pageNum=<%= pageNum %>'">	
<% }else if(id.equals("admin")){ %>
		<input type = "button" value = "글 수정" 
			onclick = "document.location.href=
			'qupdateForm.jsp?num=<%= article.getNum() %>&pageNum=<%= pageNum %>'">
			&nbsp;&nbsp;&nbsp;&nbsp;
		<input type = "button" value = "글 삭제" 
			onclick = "document.location.href=
			'qc_deleteForm.jsp?num=<%= article.getNum() %>&pageNum=<%= pageNum %>'">
			&nbsp;&nbsp;&nbsp;&nbsp;
   			<input type = "button" value = "답글쓰기" onclick = "document.location.href='qwriteForm.jsp?num=
   			<%= num %>&ref='+'<%= ref %>&re_step=<%= re_step %>&re_level=<%= re_level %>'">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type = "button" value = "글 목록" 
			onclick = "document.location.href='qlist.jsp?pageNum=<%= pageNum %>'">		
<% }else { %>
<input type = "button" value = "글 수정" 
			onclick = "document.location.href=
			'qupdateForm.jsp?num=<%= article.getNum() %>&pageNum=<%= pageNum %>'">
			&nbsp;&nbsp;&nbsp;&nbsp;
		<input type = "button" value = "글 삭제" 
			onclick = "document.location.href=
			'qc_deleteForm.jsp?num=<%= article.getNum() %>&pageNum=<%= pageNum %>'">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type = "button" value = "글 목록" 
			onclick = "document.location.href='qlist.jsp?pageNum=<%= pageNum %>'">
			<% } %>
				</td>
</tr>	
</table>
<%
	}catch(Exception e) {}
%>
</form>
</center>
</body>
</html>