<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="java.io.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!private PrintWriter logFile;

	public void jspInit() {
		String filename = "c\\data\\datatime_log.txt";
		try {
			logFile = new PrintWriter(new FileWriter(filename, true));
		} catch (IOException e) {
			System.out.printf("%TT - %s ������ �� �� �����ϴ�. %n", new GregorianCalendar(), filename);
		}
	}%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ ��¥�� �ð�</title>
</head>
<body>
	<%
		GregorianCalendar now = new GregorianCalendar();
		String date = String.format("���� ��¥ : %TY�� %TM�� %Te�� ", now, now, now);
		String time = String.format("���� ��¥ : %TI�� %Tm�� %TS�� ", now, now, now);
		out.println(date + "<BR>");
		out.println(time + "<BR>");
		if (logFile != null)
			logFile.printf("%TF %TT�� ȣ��Ǿ����ϴ� \n", now, now);
	%>
</body>
</html>
<%!public void jspDestroy() {
		if (logFile != null)
			logFile.close();
	}%>