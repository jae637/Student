<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�λ��ϱ�</title>
</head>
<body>
	<c:choose>
		<c:when test="${param.NUM==0}">
			ó�� �˰ڽ��ϴ�. <BR>
		</c:when>
		<c:when test="${param.NUM==1 }">
			�ݰ����ϴ�.<br>
		</c:when>
		<c:otherwise>
			�ȳ��ϼ���.<br>
		</c:otherwise>
	</c:choose>

</body>
</html>