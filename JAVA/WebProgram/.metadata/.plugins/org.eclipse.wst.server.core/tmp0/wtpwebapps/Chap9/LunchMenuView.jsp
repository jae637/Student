<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<HEAD>
<TITLE>���� �Ĵ�</TITLE>
</HEAD>
<BODY>
	<H3>������ ���� �޴��Դϴ�.</H3>
	<UL>
		<c:forEach var="dish" items="${MENU}">
                         <LI>${dish}</LI>
                  </c:forEach>
	</UL>
</BODY>
</HTML>
