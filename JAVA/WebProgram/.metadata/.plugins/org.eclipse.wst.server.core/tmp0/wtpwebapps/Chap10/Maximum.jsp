<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="util" tagdir="/WEB-INF/tags" %>
<HTML>
       <HEAD><TITLE>최대값 구하기</TITLE></HEAD>
       <BODY>
              <H3>최대값 구하기</H3>
              <util:max var="maximum" num1="${param.NUM1}" num2="${param.NUM2}" />
              최대값: ${maximum}
       </BODY>
</HTML>
