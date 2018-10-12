<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.DBUtil" %>
<%@ page import = "util.NoticeDBBean" %>
<%@ page import = "util.NoticeDataBean" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file = "../view/color.jsp" %>

<%!
	int pageSize = 5;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>

<%
	String pageNum = request.getParameter("pageNum");
	
	if(pageNum == null)
	{
		pageNum = "1";
	}
	
	int currentPage = Integer.parseInt(pageNum);
	System.out.println("currnetPage");
	int startRow = (currentPage -1) * pageSize;     //(currentPage * 3) - 2; (currentPage -1) * pageSize + 1;  
	int endRow = currentPage * pageSize;
	int count = 0;
	int number = 0;
	
	List noticeList = null;
	NoticeDBBean dbPro = NoticeDBBean.getInstance();
	count = dbPro.getNoticeCount();
	
	if(count > 0)
	{
		noticeList = dbPro.getNotices(startRow, endRow);
	}
	
	number = count - (currentPage - 1) * pageSize;   
%>

<link href = "style.css" rel = "stylesheet" type = "text/css">
<body bgcolor = "<%= bodyback_c %>">
<center><b>전체 글 : <%= count %></b>
<table width = "650">
<tr>	
<td align = "right" bgcolor = "<%= value_c %>">
		<%if(session.getAttribute("sessionID")==null){ %>
		<b></a></b></font>&nbsp;
		<%}else{
			String id = (String)session.getAttribute("sessionID");
			if(id.equals("admin")){%>
			<a href="n_writeForm.jsp"><font color="white"><b>글쓰기</b></a></b></font>&nbsp;
			<%}else{%>
			&nbsp;
			<%} %>
		<% } %>		
	</td>
</table>
<%
	if(count == 0)
	{
%>
<table width = "650" border = "1" cellpadding = "0" cellspacing = "0" bordercolor="#ba3e88">
<tr>
	<td align = "center">
		공지사항에 저장된 글이 없습니다.
	</td>
</table>
<%
	}
	else
	{
%>
<table border = "1" bordercolor="#ba3e88" width = "650" cellpadding = "0" cellspacing = "0" align = "center" >
<tr height = "30" bgcolor = "<%= value_c %>">
	<td align = "center" width = "30">번 호</td>
	<td align = "center" width = "150">제 목</td>
	<td align = "center" width = "80">작성자</td>
	<td align = "center" width = "100">작성일</td>
	<td align = "center" width = "30">조 회</td>
</tr>
<%
		for(int i =0; i < noticeList.size(); i++)
		{
			NoticeDataBean notice = (NoticeDataBean)noticeList.get(i);
%>
<tr height = "30">
	<td align = "center" width = "50"><%= number-- %></td>
	<td align = "left" width= "50">
	
	<a href = "n_content.jsp?num=<%= notice.getNum() %>&pageNum=<%= currentPage %>">
			<%= notice.getSubject() %>
		</a>

	</td>
	<td align = "center" width = "50"><%=notice.getWriter() %></td>
	<td align = "center" width = "150"><%= sdf.format(notice.getReg_date()) %></td>
	<td align = "center" width = "50"><%= notice.getReadcount() %></td>
</tr>
<%
		}
%>
</table>
<% 
	}
%>
<%
	if(count > 0)
	{
		// 전체 페이지의 수를 연산
		int pageCount = count / pageSize + (count % pageSize == 0?0:1);
		int startPage = (int)(currentPage / 5) * 5 + 1;
		int pageBlock = 5;
		int endPage = startPage + pageBlock - 1;
		
		if(endPage > pageCount)
			endPage = pageCount;
		
		if(startPage > 5)
		{
%>
			<a href = "n_list.jsp?pageNum=<%= startPage-5 %>">[이전]</a>
<%
		}
		
		for(int i = startPage; i <= endPage; i++)
		{
%>
			<a href = "n_list.jsp?pageNum=<%= i %>">[<%= i %>]</a>
<%
		}
		
		if(endPage < pageCount)
		{
%>
			<a href = "n_list.jsp?pageNum=<%= startPage+5 %>">[다음]</a>
<%
		}
	}
%>
      
</body>
</html>
