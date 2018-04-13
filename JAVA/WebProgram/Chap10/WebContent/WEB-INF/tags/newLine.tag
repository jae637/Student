<%@tag body-content="empty"%>
<%@attribute name="color"%>
<%@attribute name="size" type="java.lang.Integer"%>
<FONT color=${color}>
<%
	for(int cnt=0;cnt<size;cnt++)
		out.print("-");
%>
</FONT><br>