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
	<table border="1" summary="�Խ��� ���">
		<colgroup>
			<col width="70" />
			<col width="500" />
			<col width="100" />
			<col width="150" />
			<col width="90" />
		</colgroup>
		<thead>
			<tr>
				<th style="text-align: center">��ȣ</th>
				<th style="text-align: center">����</th>
				<th style="text-align: center">�ۼ���</th>
				<th style="text-align: center">��� �Ͻ�</th>
				<th style="text-align: center">��ȸ��</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center">1</td>
				<td><a href="boardView.jsp">���ع��� ��λ��� ������ �⵵�� ��...</a></td>
				<td align="center">�迬��</td>
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
		<input type="button" value="���" onclick="goUrl('index.html');" />
		<input type="button" value="�۾���"
			onclick="goUrl('boardWriteForm.jsp');" />
	<form name="searchForm" action="" method="get">
		<select name="searchType">
			<option value="ALL">��ü�˻�</option>
			<option value="SUBJECT">����</option>
			<option value="WRITER">�ۼ���</option>
			<option value="CONTENTS">����</option>
		</select> <input type="text" name="searchText" value="" /> <input
			type="submit" value="�˻�" />
	</form>
	</p>
	
</body>
</html>