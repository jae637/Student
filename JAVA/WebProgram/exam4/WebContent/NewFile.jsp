<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="java.io.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!private PrintWriter logFile;

	public void jspInit() {
		String filename = "c\\data\\datatime_log.txt";
		try {
			logFile = new PrintWriter(new FileWriter(filename, true));
		} catch (IOException e) {
			System.out.printf("%TT - %s 파일을 열 수 없습니다. %n", new GregorianCalendar(), filename);
		}
	}%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>현재의 날짜와 시각</title>
</head>
<body>
	<%
		GregorianCalendar now = new GregorianCalendar();
		String date = String.format("현재 날짜 : %TY년 %TM월 %Te일 ", now, now, now);
		String time = String.format("현재 날짜 : %TI시 %Tm분 %TS초 ", now, now, now);
		out.println(date + "<BR>");
		out.println(time + "<BR>");
		if (logFile != null)
			logFile.printf("%TF %TT에 호출되었습니다 \n", now, now);
	%>
</body>
</html>
<%!public void jspDestroy() {
		if (logFile != null)
			logFile.close();
	}%>