<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ include file = "../view/color.jsp" %>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
%>

<html>
<head><title>��������</title></head>
<link href = "style.css" rel = "stylesheet" type = "text/css">
<script language = "javascript">
<!--
	function deleteSave()
	{
		if(document.delForm.password.value == "")
		{
			alert("��й�ȣ�� �Է��ϼ���.");
			document.delForm.password.focus();
			return false;
		}
	}
//-->
</script>

<body bgcolor = "<%= bodyback_c %>">
<center><b>�� ����</b></center><br>
<form method = "post" name = "delForm"
	action = "n_deletePro.jsp?pageNum=<%= pageNum %>"
	onSubmit = "return deleteSave()">
<table border = "1" align = "center" cellspacing = "0" cellpadding = "0" width = "360">
<tr height = "30">
	<td align = "center" bgcolor = "<%= value_c %>">
		<b>��й�ȣ�� �Է��ϼ���.</b>
	</td>
</tr>
<tr height = "30">
	<td align = "center">��й�ȣ :
		<input type = "password" name = "password" size = "8" maxlength = "12">
		<input type = "hidden" name = "num" value = "<%= num %>">
	</td>
</tr>
<tr height = "30">
	<td align = "center" bgcolor = "<%= value_c %>">
		<input type = "submit" value = "�� ����">
		<input type = "button" value = "�� ���"
			onclick = "document.location.href='n_list.jsp?pageNum=<%= pageNum %>'">
	</td>
</tr>
</table>
</form>
</body>
</html>