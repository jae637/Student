<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<jsp:useBean class="mall.ProductInfo" id="pinfo" scope="request">
코드:<jsp:getProperty property="code" name="pinfo" />
	<br>제품명:<jsp:getProperty property="name" name="pinfo" />
	<br>가격:<jsp:getProperty property="price" name="pinfo" />
	<br>
</jsp:useBean>