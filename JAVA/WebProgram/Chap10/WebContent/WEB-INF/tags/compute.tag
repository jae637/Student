<%@tag pageEncoding="euc-kr"%>
<%@tag body-content="scriptless"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="var" required="true" rtexprvalue="false"%>
<%@attribute name="start" type="java.lang.Integer"%>
<%@attribute name="end" type="java.lang.Integer"%>
<%@variable name-from-attribute="var" alias="number"
	variable-class="java.lang.Integer" scope="NESTED"%>
<%
	for (int cnt = start; cnt <= end; cnt++) {
%>
<c:set var="number" value="<%=cnt%>" />
<jsp:doBody/>
<%
	}
%>
