<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<c:set var= "num1" value= "7" />
<c:set var= "num2" value= "9" />
<c:set var= "result" value= "${num1*num2}" />
<HTML>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
        <HEAD><TITLE>���� ���α׷�</TITLE></HEAD>
        <BODY>
                  ${num1}�� ${num2}�� ����? ${result}
        </BODY>
</HTML>
