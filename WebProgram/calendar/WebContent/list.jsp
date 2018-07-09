<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.BoardDBBean" %>
<%@ page import = "util.BoardDataBean" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file = "../view/color.jsp" %>

<%!
	int pageSize = 5;  //ȭ�鿡 ǥ���� �� ����� ����
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>

<%
	//list.jsp���� �Ѿ���� pageNum ���� �޾Ƽ� ������ 1�� ����
	String pageNum = request.getParameter("pageNum");
	
	if(pageNum == null)
	{
		pageNum = "1";
	}
	
	int currentPage = Integer.parseInt(pageNum);    //���ڿ��� ����ȭ
	System.out.println("currnetPage");
	int startRow = (currentPage -1) * pageSize;     //(currentPage * 3) - 2; (currentPage -1) * pageSize + 1;  
	int endRow = currentPage * pageSize;
	int count = 0;  //�� ���ڵ� ��
	int number = 0; // �������� �����ϴ� �� ó���� ������ �Խù� ��ȣ
	
	List articleList = null;  //ȭ�鿡 ����� ���ڵ� ������
	BoardDBBean dbPro = BoardDBBean.getInstance();
	count = dbPro.getArticleCount();  //��ü ���ڵ� �� ����
	
	if(count > 0)
	{
		articleList = dbPro.getArticles(startRow, endRow);
	}
	
	number = count - (currentPage - 1) * pageSize;   
%>

<html>
<head><title>�⼮üũ</title></head>
<link href = "style.css" rel = "stylesheet" type = "text/css">
<body bgcolor = "<%= bodyback_c %>">
<center><b>�⼮üũ (��ü �� : <%= count %>)</b>
<table width = "650">
<tr>
	<td align = "right" bgcolor = "<%= value_c %>">
		<a href = "writeForm.jsp">�۾���</a>
	</td>
</table>
<%     //board ���̺� ����� ���ڵ尡 ������
	if(count == 0)
	{
%>
<table width = "650" border = "1" cellpadding = "0" cellspacing = "0" bordercolor="#ba3e88">
<tr>
	<td align = "center">
		�⼮üũ�� ����� ���� �����ϴ�.
	</td>
</table>
<%
	}
	else    //board ���̺� ����� ���ڵ尡 ������
	{
%>
<table border = "1" bordercolor="#ba3e88" width = "650" cellpadding = "0" cellspacing = "0" align = "center" >
<tr height = "30" bgcolor = "<%= value_c %>">
	<td align = "center" width = "30">�� ȣ</td>
	<td align = "center" width = "150">�� ��</td>
	<td align = "center" width = "80">�ۼ���</td>
	<td align = "center" width = "100">�ۼ���</td>
	<td align = "center" width = "30">�� ȸ</td>
</tr>
<%		//�� ����� �� �پ� ȭ�鿡 ǥ���ϴ� �κ�. articleList.size()�� �� ����� ����� ���Ͽ� �ݺ�.
		for(int i =0; i < articleList.size(); i++)
		{
			BoardDataBean article = (BoardDataBean)articleList.get(i);
%>
<tr height = "30">
	<td align = "center" width = "50"><%= number-- %></td>
	<td width = "250">
<%			// �亯���̶�� �鿩���� �Ѵ�.
			int wid = 0;   //������ ����ϱ� ���� ���� ����
		
			if(article.getRe_level() > 0) // �亯���̶��...
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
%>		<!--  ���� ��Ͽ� �� �پ� ǥ���Ѵ�. ���� ������ content.jsp�� �̵��Ͽ� ���� ������ �� �� �ֵ��� ��ũ�� �ɾ��� -->
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
<!--  �������� �̵��ϴ� ��ũ�� ǥ���Ѵ�. -->
<%
	if(count > 0)
	{
		// ��ü �������� ���� ����
		int pageCount = count / pageSize + (count % pageSize == 0?0:1);
		int startPage = (int)(currentPage / 5) * 5 + 1;
		int pageBlock = 5;
		int endPage = startPage + pageBlock - 1;
		
		if(endPage > pageCount)
			endPage = pageCount;
		
		if(startPage > 5)
		{
%>
			<a href = "list.jsp?pageNum=<%= startPage-5 %>">[����]</a>
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
			<a href = "list.jsp?pageNum=<%= startPage+5 %>">[����]</a>
<%
		}
	}
%>
</center>
</body>
</html>
