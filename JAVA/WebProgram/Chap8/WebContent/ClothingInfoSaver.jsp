<%@page contentType= "text/html; charset=euc-kr "%>
<jsp:useBean class= "mall.ClothingInfo " id= "pinfo " scope= "request "/>
<jsp:setProperty name= "pinfo" property= "code " value= "70002 " />
<jsp:setProperty name= "pinfo" property= "name " value= "반팔 티셔츠 " />
<jsp:setProperty name= "pinfo" property= "price " value= "15000 " />
<jsp:setProperty name= "pinfo" property= "size " value= "M " />
<jsp:setProperty name= "pinfo" property= "color " value= "베이지 " />
<HTML>
      <HEAD><TITLE>의류 정보 관리</TITLE></HEAD>
      <BODY>
            의류 정보가 저장되었습니다. <BR>
            --------------------------<BR>
            <H3>제품 개략 정보</H3>
            <jsp:include page= "ProductInfo.jsp " />
      </BODY>
</HTML>
