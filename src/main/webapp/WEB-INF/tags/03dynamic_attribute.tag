<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="attr1" required="true" %>
<%@ tag dynamic-attributes="at" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--03dynamic_attribute 
	attribute에 정의 되지 않은 속성들은
	다 dynamic-attirbutes에 Map 형태로 저장 
	
	at1="at1value" at2="at2value" at3="at3value"
	dynamic-attributes at라는 Map에  
	 <key,value> =  <"at1","at1value">
	             	<"at2","at2value">	,<"at3","at3value">										
 -->
 
 ${attr1 }  <br>
 <select name="at">
	 <c:forEach items="${at }" var="option">
	 	<option value="${option.value }">${option.key} : ${option.value }</option>
	 </c:forEach>
 </select>
 
 