<%@tag import="java.text.SimpleDateFormat"%>
<%@tag import="java.util.Calendar"%>
<%@ tag language="java" pageEncoding="UTF-8"%>




<%
	// 20-06-02
	SimpleDateFormat sdf=new SimpleDateFormat("YY-MM-dd");
%>
<%=sdf.format(Calendar.getInstance().getTime())%>

<!-- 00now -->