<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean class="mall.BookInfo" id="pinfo" scope="request">
	<jsp:setProperty name="pinfo" property="code" value="50001" />
	<jsp:setProperty name="pinfo" property="name" value="의로인" />
	<jsp:setProperty name="pinfo" property="price" value="9000" />
	<jsp:setProperty name="pinfo" property="writer" value="존 그리샴" />
	<jsp:setProperty name="pinfo" property="page" value="704" />
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>책 정보 관리</title>
</head>
<body>
	책 정보가 저장되었습니다.
	<br> -----------------
	<br>
	<h3>제품 개략 정보</h3>
	<jsp:include page="ProductInfo.jsp"></jsp:include>
</body>
</html>