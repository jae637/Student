<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	X=${param.NUM1},Y= ${param.NUM2 }
	<br>
	<br> X+Y=${param.NUM1+param.NUM2 }
	<br> X - Y = ${param.NUM1 - param.NUM2}
	<BR> X * Y = ${param.NUM1 * param.NUM2}
	<BR> X / Y = ${param.NUM1 / param.NUM2}
	<BR> X % Y = ${param.NUM1 % param.NUM2}
	<BR>
	<BR> X�� �� Ů�ϱ�? ${param.NUM1 - param.NUM2 > 0}
	<BR> Y�� �� Ů�ϱ�? ${param.NUM1 - param.NUM2 < 0}
	<BR>
	<BR> X�� Y�� ��� ����Դϱ�? ${(param.NUM1 > 0) && (param.NUM2 > 0)}
	<BR>
	<BR> X�� Y�� �����ϱ�? ${param.NUM1 == param.NUM2? "��" : "�ƴϿ�"}
	<BR>
	<BR>

</body>
</html>