<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean class="mall.BookInfo" id="pinfo" scope="request">
	<jsp:setProperty name="pinfo" property="code" value="50001" />
	<jsp:setProperty name="pinfo" property="name" value="�Ƿ���" />
	<jsp:setProperty name="pinfo" property="price" value="9000" />
	<jsp:setProperty name="pinfo" property="writer" value="�� �׸���" />
	<jsp:setProperty name="pinfo" property="page" value="704" />
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>å ���� ����</title>
</head>
<body>
	å ������ ����Ǿ����ϴ�.
	<br> -----------------
	<br>
	<h3>��ǰ ���� ����</h3>
	<jsp:include page="ProductInfo.jsp"></jsp:include>
</body>
</html>