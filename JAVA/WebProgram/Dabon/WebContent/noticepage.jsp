<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<title>Dabon SE</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/dabon.css">
</head>
<body id=contents>
	<table border="1" summary="게시판 목록">
		<colgroup>
			<col width="70" />
			<col width="500" />
			<col width="100" />
			<col width="150" />
			<col width="90" />
		</colgroup>
		<thead>
			<tr>
				<th style="text-align: center">번호</th>
				<th style="text-align: center">제목</th>
				<th style="text-align: center">작성자</th>
				<th style="text-align: center">등록 일시</th>
				<th style="text-align: center">조회수</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center">1</td>
				<td><a href="boardView.jsp">동해물과 백두산이 마르고 닳도록 하...</a></td>
				<td align="center">김연석</td>
				<td align="center">2013.06.24</td>
				<td align="center">10</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td align="center" colspan="5">1</td>
				
			</tr>
		</tfoot>
	</table>
	<p>
		<input type="button" value="목록" onclick="goUrl('index.html');" />
		<input type="button" value="글쓰기"
			onclick="goUrl('boardWriteForm.jsp');" />
	<form name="searchForm" action="" method="get">
		<select name="searchType">
			<option value="ALL">전체검색</option>
			<option value="SUBJECT">제목</option>
			<option value="WRITER">작성자</option>
			<option value="CONTENTS">내용</option>
		</select> <input type="text" name="searchText" value="" /> <input
			type="submit" value="검색" />
	</form>
	</p>
	
</body>
</html>