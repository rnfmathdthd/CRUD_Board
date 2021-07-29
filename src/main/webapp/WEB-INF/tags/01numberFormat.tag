<%@tag import="java.text.DecimalFormat"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="number" required="true" type="java.lang.String"  %>
<%@ attribute name="format" required="true" type="java.lang.String" %>
<!-- 
	jsp에서 number,format속성의 값들은
	tag파일에서 변수, el로 사용가능 
 -->
 <%
 	DecimalFormat df=new DecimalFormat(format);//00000.###
 	String formatNumber=
 			df.format(Double.parseDouble(number));
 	out.print(formatNumber);
 %>

