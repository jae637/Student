<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="util" tagdir="/WEB-INF/tags" %>
<HTML>
       <HEAD><TITLE>�ִ밪 ���ϱ�</TITLE></HEAD>
       <BODY>
              <H3>�ִ밪 ���ϱ�</H3>
              <util:max var="maximum" num1="${param.NUM1}" num2="${param.NUM2}" />
              �ִ밪: ${maximum}
       </BODY>
</HTML>
