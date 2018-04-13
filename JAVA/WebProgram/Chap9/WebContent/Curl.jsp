<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<c:url var="myUrl" value="Ccatch.jsp">
	<c:param name="NUM1" value="999"></c:param>
	<c:param name="NUM2" value="333"></c:param>
</c:url>
<c:redirect url="${myUrl}"></c:redirect>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
