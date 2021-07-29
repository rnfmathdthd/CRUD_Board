
<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.service.CommCodeServiceImpl"%>
<%@page import="com.study.code.service.ICommCodeService"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>





	<div class="alert alert-warning">해당 멤버를 찾을 수 없습니다</div>
	<a href="memberList.wow" class="btn btn-default btn-sm"> <span
		class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;목록
	</a>



	<div class="container">
		<h3>회원 정보 수정</h3>
		
		<form:form action="modify.wow" method="post" modelAttribute="member">
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<th>아이디</th>
						<td>${member.memId }<input type="hidden" name="memId"
							value="${member.memId }"></td>
							<form:hidden path="memId"/>
							<form:errors path="memId"/>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><form:password path="memPass" cssClass="form-control input-sm"/>
						<span class="text-danger"> <span
								class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						</span>
								</td>
								<form:errors path="memPass"/>
					</tr>
					<tr>
						<th>회원명</th>
						<td>
						<form:input path="memName" cssClass="form-control input-sm"/>
							</td>
						<form:errors path="memName"/>
					</tr>
					<tr>
						<th>우편번호</th>
						<td>
						<form:input path="memZip" cssClass="form-control input-sm"/>
						</td>
						<form:errors path="memZip"/>
					</tr>
					<tr>
						<th>주소</th>
						<td>
							<form:input path="memAdd1" cssClass="form-control input-sm"/>
							<form:input path="memAdd2" cssClass="form-control input-sm"/>
							</td>
							<form:errors path="memAdd1"/>
							<form:errors path="memAdd2"/>
					</tr>
					<tr>
						<th>생일</th>
						<td><input type="date" name="memBir"
							class="form-control input-sm" value='${member.memBir }'></td>
					</tr>
					<tr>
						<th>메일</th>
						<td>
							<form:input path="memMail" cssClass="form-control input-sm"/>
							</td>
							<form:errors path="memMail"/>
					</tr>
					<tr>
						<th>핸드폰</th>
						<td>
						<form:input path="memHp" cssClass="form-control input-sm"/>
						</td>
							
					</tr>
					<tr>
						<th>직업</th>
						<td>
						<form:select path="memJob">
						<form:option value="">--선택하세요--</form:option>
						<form:options items="${jobList }" itemLabel="commNm" itemValue="commCd"/>
						</form:select>
						<form:errors path="memJob"/>
						</td>
						
					</tr>
					<tr>
						<th>취미</th>
						<td>
						<form:select path="memHobby">
						<form:option value="">--선택하세요--</form:option>
						<form:options items="${hobbyList }" itemLabel="commNm" itemValue="commCd"/>
						</form:select>
						<form:errors path="memHobby"/>
						</td>
					</tr>
					<tr>
						<th>마일리지</th>
						<td>${member.memMileage }</td>
					</tr>
					<tr>
						<th>탈퇴여부</th>
						<td>${member.memDelYn }</td>
					</tr>
					<tr>
						<td colspan="2"><a href="<%=request.getContextPath() %>/edit.wow"
							class="btn btn-info btn-sm"> <span
								class="glyphicon glyphicon-list" aria-hidden="true"></span>
								&nbsp;이전으로
						</a>
							<button type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
								&nbsp;&nbsp; 저장
							</button>

							<button type="submit" formaction="delete.wow"
								class="btn btn-danger btn-sm">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								&nbsp;&nbsp; 삭제
							</button></td>
					</tr>
				</tbody>
			</table>

		</form:form>
	</div>


</body>
</html>