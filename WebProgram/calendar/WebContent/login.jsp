<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--<%@ page contentType="text/html;charset=utf-8"%>-->
<title>coduk_coduk</title>


<style type="text/css">
//여기서부터 복붙1
@import url(https://fonts.googleapis.com/css?family=Open+Sans:400,300,700);

* {
  box-sizing:border-box;
  outline:0;
}
body {
	background: #EEE;
	font-family: 'Open Sans', sans-serif;
	font-size: 13px;
	background-image: url(images/bg.gif);
	background-color: #FFF;
}
a, li {  
  transition: all 0.5s ease;
  -webkit-transition: all 0.5s ease;
}
a {color:white; text-decoration:none;}
li {list-style-type: none;}
nav {
  width: 180px;
  height: auto;
  float:left;
  position:relative;
  background:#FFB1BC;
  margin:0 auto;
  padding: 10px 0;
  color: white;
  line-height:30px;
  border-radius:5px;
  box-shadow: -2px 1px 3px 0px rgba(0, 0, 0, 0.5);
}
nav ul li {
  position:relative;
  padding: 0 20px;

}
nav ul li:hover {
  background: crimson;
  border-left: 5px solid #B31131;
}
nav ul li:hover:before {
  content: '';
  border-color: transparent #EEE transparent transparent ;
  right: 0;
  border-width:10px;
  position: absolute;
  border-style: solid;
  top: 5px;
}
//여기까지 복붙1
//여기부터 복붙2

.wrapper {
  width:450px;
  margin:80px auto;
}
header {
  width:260px;
  height: 140px;
  padding-top:45px;
  float:left;
  font-family: 'Sansita One';
  font-weight: 400;
  font-size: 28px;
  text-align: center;
  color: crimson;
  line-height:28px;
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.4);
}
header p {
  font-size: 15px;
  line-height: 15px;
  margin-left:70px;
}
footer {
  width:100%;
  height:50px;
  float:left;
  overflow:hidden;
  margin-top:80px;
}
footer > p {
  line-height:40px;
  vertical-align:middle;
  color: #FFB1BC;
  font-size:11px;
  text-align:center;
}
footer a:link, footer a:visited, footer a:active {
  color:crimson;
  text-decoration:none;
}
footer a:hover {
  width:200px;
  color: white;
  background: crimson;
}
.active {
  background: crimson;
  border-left: 5px solid #B31131;
}
.active:before {
  content: '';
  border-color: transparent #EEE transparent transparent ;
  right: 0;
  border-width:10px;
  position: absolute;
  border-style: solid;
  top: 5px;
}
    body,td,th {
	font-family: "Open Sans", sans-serif;
}
</style>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

</head>

<body onload="MM_preloadImages('images/rollup2.png')">
  <div class="wrapper">


<!--
body,td,th {
	font-size: 12px;
	color: #999;
	font-weight: bold;
}
.leftcate {
	color: #FFF;
	font-size: 20px;
}
.leftsub {
	font-size: 17px;
}
.newstitle {
	color: #06C;
	font-size: 30px;
}
#apDiv1 {
	position: absolute;
	width: 98px;
	height: 89px;
	z-index: 1;
	left: 31px;
	top: 853px;
}
#apDiv2 {
	position: absolute;
	width: 171px;
	height: 98px;
	z-index: 1;
	left: 157px;
	top: 716px;
	color: #FFF;
	text-align: center;
	background-color: rgba(0,0,0,0.5);
	overflow: auto;
}
#apDiv2 p strong {
	font-size: 36px;
}
#apDiv2 p strong {
	text-align: center;
}
#apDiv2 p strong {
	font-size: 36;
}
#apDiv2 p strong {
	font-size: 30px;
}
#apDiv3 {
	position: absolute;
	width: 521px;
	height: 83px;
	z-index: 2;
	left: 397px;
	top: 847px;
	font-size: 14px;
	color: #FFF;
	background-color: rgba(0,0,0,0.5);
	text-align: center;
}
.leftsub .leftcate {
	font-family: a펜글씨B;
}
.leftsub .leftcate {
	font-family: a엄마의편지B;
}
.leftcate {
	font-family: a엄마의편지B;
}
#apDiv4 {
	position: absolute;
	width: 417px;
	height: 79px;
	z-index: 3;
	left: 616px;
	top: 29px;
	background-color: rgba(0,0,0,0.5);
}
#apDiv4 {
	font-size: 36px;
}
#apDiv4 {
	color: #CCC;
}
#apDiv4 {
	font-family: Papyrus;
	text-align: right;
	color: #FFF;
}
a:link {
	color: #FFF;
	text-decoration: none;
}
a:visited {
	color: #FFF;
	text-decoration: none;
}
a:hover {
	color: #F6C;
	text-decoration: underline;
}
a:active {
	color: #000;
	text-decoration: none;
}
body {
	background-image: url(../snow.gif);
	background-color: #000;
}
.banner {
	float: left;
	height: 100px;
	width: 80px;
}
-->
</style>
<script type="text/javascript">
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
</script>
</head>

<body onload="MM_preloadImages('images/rollup2.png')">
<script>
      function checkCmp(cmp){
    	  var s = location.href;   //현재 내가 있는 페이지의 URL 구하기
    	  if(s != "http://localhost:8080/calendar/loginAft.jsp"){
    		  alert('로그인 후 이용하시기 바랍니다.');
    		  cmp = "login.jsp"
    	  }
    	  location.href = cmp;
      }
</script> 



<table width="820" border="0" align="center" cellpadding="2" cellspacing="0" style="border-style:solid; border-color:#CCC; border-width:1px; background-color:rgba(255,255,255,1)">
  <tr>
    <td align="left" bgcolor="#000000"><img src="images/upper.gif" width="786" height="94" /></tr>
  <tr>
    <td height="15" align="right" bgcolor="#000000"><img src="images/login.png" width="100" height="25" border="0" usemap="#Map" /> <img src="images/join.png" width="100" height="25" border="0" usemap="#Map2" /></td>
  </tr>
  <tr>
    <td style="padding:10px;"><table width="819" cellspacing="5" cellpadding="0" style="border:thin; border-style:solid; border-width:1px; border-color:#DDD;">
      <tr>
       <td width="180" height="250" bgcolor="#000000"><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image3','','images/rollup2.png',1)"><img src="images/rollup1.png" name="Image3" width="180" height="250" border="0" id="Image3" /></a></td>
        <td width="1" rowspan="2" valign="top">&nbsp;</td>
        <td rowspan="2" align="center" valign="top">&nbsp;
          <p><img src="images/login_logo.jpg" width="677" height="322" alt="login_logo" /></p>

	      <center>
		<form name="insertFrm" id = "insertFrm" method="post" action="loginPro.jsp" >
		<table border="3" bordercolor="pink">
			<tr>
			<td bgcolor="pink">아이디</td><td><input type="text" name="id"></td></tr>
			<tr>
			<td bgcolor="pink">비밀번호</td><td><input type="password" name="password"/>
			</td></tr>
		</table><br />
			<input type="button" value="로그인" id="insert" OnClick = "javascript:Insert();">
			<input type="button" value="회원가입" onclick="location.href='join.jsp'">
			<input type="button" value="아이디찾기" onclick="location.href='findId.jsp'">
			<input type="button" value="비밀번호찾기" onclick="location.href='findPassword.jsp'">
		</form></center>

	        <script type="text/javascript">
		 function Insert()
	        {
	        var insertFrm = document.insertFrm;
	        if( !insertFrm.id.value ){
	          alert( "아이디를 입력하세요." );
	          insertFrm.id.focus();
	          return;
	         }

	        if( !insertFrm.password.value ){
	          alert( "비밀번호를 입력하세요." );
	          insertFrm.password.focus();
	          return;
	         }

	        insertFrm.submit();
	        }
          </script>     
       
        <td width="1" rowspan="2" valign="top">&nbsp;</td>
        <td width="25" height="10" rowspan="2" valign="top" bgcolor="#D6D6D6"></iframe>          &nbsp;</td>
      </tr>
      <tr>
      
        <td width="180"><table width="180" border="0" cellpadding="7" cellspacing="2">
         	<nav role='navigation'>
			  <ul>
			    <li class="active"><a href="12-03-final.html">Main</a></li>
			    <li><a href="mSS.jsp">이달의 신상</a></li>
			    <li><a href="mSI.jsp">이달의 세일정보</a></li>
			    <li><a href="loginAl.jsp">이벤트</a></li>
			    <li><a href="login.jsp" onclick="checkCmp('http://localhost:8080/calendar/login.jsp')">출석체크</a></li>
			    <li><a href="ncheck.jsp">공지사항</a></li>
			    <li><a href="q&a.html">Q&A</a></li>
			  </ul>
			</nav>
        </table>
        <p>&nbsp;</p>
          <img src="images/banner.gif" width="180" height="130" alt="banner" />
          <p>&nbsp;</p>
          </td>
          <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  
  <tr>
    <td align="right" bgcolor="#000000"><a href="loginAl.jsp">탈퇴</td>
  </tr>
  <tr bgcolor="#000000">
    <td height="104"><img src="images/lower.gif" width="786" height="94" /></td>
  </tr>
</table>

<map name="Map" id="Map">
  <area shape="rect" coords="-1,0,98,21" href="login.jsp" alt="로그인" title="로그인"/>
</map>

<map name="Map2" id="Map2">
  <area shape="rect" coords="-1,1,99,23" href="join.jsp" alt="회원가입" title="회원가입"/>
</map>
</body>
</html>
