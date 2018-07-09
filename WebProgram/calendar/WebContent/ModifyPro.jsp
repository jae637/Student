<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 자바빈 클래스 import --%>      
<%@ page import="util.*" %>

<% request.setCharacterEncoding("utf-8"); %>
<html>
<head>
	<title>회원정보 수정처리</title>
</head>
<body> 
<%-- 자바빈 관련 액션태그 사용 --%>

<jsp:useBean id="member" class="util.SignupDTO" />
<jsp:setProperty property="*" name="member"/>	

<%
	// 세션에서 아이디를 가져와 MemberBean에 세팅한다.
	String id= (String)session.getAttribute("sessionID"); 
	member.setId(id);

	// 수정할 회원정보를 담고있는 MemberBean을 DAO로 전달하여 회원정보 수정을 한다.
	DBUtil dbu = DBUtil.getInstance();
	dbu.updateMember(member);
%>

<script>
alert("회원정보수정이 완료되었습니다.");
window.location.href="loginAft.jsp";
</script>
</body>
</html>