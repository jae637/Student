<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.BoardDBBean" %>
<%@ page import = "util.BoardDataBean" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file = "../view/color.jsp" %>

<%!
	int pageSize = 5;  //화면에 표시할 글 목록의 개수
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>

<%
	//list.jsp에서 넘어오는 pageNum 값을 받아서 없으면 1로 지정
	String pageNum = request.getParameter("pageNum");
	
	if(pageNum == null)
	{
		pageNum = "1";
	}
	
	int currentPage = Integer.parseInt(pageNum);    //문자열의 정수화
	System.out.println("currnetPage");
	int startRow = (currentPage -1) * pageSize;     //(currentPage * 3) - 2; (currentPage -1) * pageSize + 1;  
	int endRow = currentPage * pageSize;
	int count = 0;  //총 레코드 수
	int number = 0; // 페이지별 시작하는 맨 처음에 나오는 게시물 번호
	
	List articleList = null;  //화면에 출력할 레코드 데이터
	BoardDBBean dbPro = BoardDBBean.getInstance();
	count = dbPro.getArticleCount();  //전체 레코드 수 저장
	
	if(count > 0)
	{
		articleList = dbPro.getArticles(startRow, endRow);
	}
	
	number = count - (currentPage - 1) * pageSize;   
%>

<html>
<head><title>출석체크</title></head>
<link href = "style.css" rel = "stylesheet" type = "text/css">
<body bgcolor = "<%= bodyback_c %>">
<center><b>출석체크 (전체 글 : <%= count %>)</b>
<table width = "650">
<tr>
	<td align = "right" bgcolor = "<%= value_c %>">
		<a href = "writeForm.jsp">글쓰기</a>
	</td>
</table>
<%     //board 테이블에 저장된 레코드가 없으면
	if(count == 0)
	{
%>
<table width = "650" border = "1" cellpadding = "0" cellspacing = "0" bordercolor="#ba3e88">
<tr>
	<td align = "center">
		출석체크에 저장된 글이 없습니다.
	</td>
</table>
<%
	}
	else    //board 테이블에 저장된 레코드가 있으면
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
<%		//글 목록을 한 줄씩 화면에 표시하는 부분. articleList.size()는 글 목록의 사이즈를 구하여 반복.
		for(int i =0; i < articleList.size(); i++)
		{
			BoardDataBean article = (BoardDataBean)articleList.get(i);
%>
<tr height = "30">
	<td align = "center" width = "50"><%= number-- %></td>
	<td width = "250">
<%			// 답변글이라면 들여쓰기 한다.
			int wid = 0;   //공백을 계산하기 위한 변수 선언
		
			if(article.getRe_level() > 0) // 답변글이라면...
			{
				wid = 5 * (article.getRe_level());
%>
		<img src = "images/level.gif" width = "<%= wid %>" height = "16">
		<img src = "images/re.gif">
<%
			}
			else
			{
%>
		<img src = "images/level.gif" width = "<%= wid %>" height = "16">
<%
			}
%>		<!--  글을 목록에 한 줄씩 표시한다. 글의 제목을 content.jsp로 이동하여 글의 내용을 볼 수 있도록 링크를 걸어줌 -->
		<a href = "content.jsp?num=<%= article.getNum() %>&pageNum=<%= currentPage %>">
			<%= article.getSubject() %>
		</a>
<%
			if(article.getReadcount() >= 20)
			{
%>
		<img src = "images/hot.gif" border = "0" height = "16">
<%
			}
%>
	</td>
	<td align = "center" width = "50"><%=article.getWriter() %></td>
	<td align = "center" width = "150"><%= sdf.format(article.getReg_date()) %></td>
	<td align = "center" width = "50"><%= article.getReadcount() %></td>
</tr>
<%
		}
%>
</table>
<% 
	}
%>
<!--  페이지를 이동하는 링크를 표시한다. -->
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
			<a href = "list.jsp?pageNum=<%= startPage-5 %>">[이전]</a>
<%
		}
		
		for(int i = startPage; i <= endPage; i++)
		{
%>
			<a href = "list.jsp?pageNum=<%= i %>">[<%= i %>]</a>
<%
		}
		
		if(endPage < pageCount)
		{
%>
			<a href = "list.jsp?pageNum=<%= startPage+5 %>">[다음]</a>
<%
		}
	}
%>
</center>
</body>
</html>
