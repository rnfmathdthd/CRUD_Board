<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>회원가입 2단계</title>
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
	<div class="container">
		<form:form action="step3.wow" method="post" modelAttribute="member">
			<div class="row col-md-8 col-md-offset-2">
				<div class="page-header">
					<h3>회원가입 2단계</h3>
				</div>
				<table class="table">
					<colgroup>
						<col width="20%" />
						<col />
					</colgroup>
					<tr>
						<th>ID</th>
						<td><form:input path="memId" id="idCheckInput" cssClass="form-control input-sm"/> <form:errors path="memId"/>
						<button onclick="fn_idCheck()" id="btn_Check">중복체크</button></td>
						<form:errors />
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><form:password path="memPass" cssClass="form-control input-sm"/> <form:errors path="memPass"/></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><form:password path="memPassConfirm" cssClass="form-control input-sm"/> <form:errors path="memPassConfirm"/></td>
					</tr>
					<tr class="form-group-sm">
						<th>회원명</th>
						<td><form:input path="memName" cssClass="form-control input-sm"/> <form:errors path="memName"/></td>
					</tr>
					<tr class="form-group-sm">
						<th>이메일</th>
						<td><form:input path="memMail" cssClass="form-control input-sm" id="mailInput"/> <form:errors path="memMail"/><button onclick="fn_mail()">인증메일 발송</button></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="pull-left">
								<a href="cancel" class="btn btn-sm btn-default"> <span
									class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									&nbsp;&nbsp;취 소
								</a>
							</div>
							<div class="pull-right">
								<button type="submit" class="btn btn-sm btn-primary">
									<span class="glyphicon glyphicon-chevron-right"
										aria-hidden="true"></span> &nbsp;&nbsp;다 음
								</button>
							</div>
						</td>
					</tr>
				</table>

			</div>
		</form:form>
	</div>
	<!-- END : 메인 콘텐츠  컨테이너  -->
</body>
<script type="text/javascript">
	function fn_mail() {
		event.preventDefault();
		// 아작스로 넘겨줄 때, 사용자가 입력한 값을 넘겨줘야함
		var mailInput = $("#mailInput").val();
 		$.ajax({
			type: "POST",
			url: "mail.wow",			// 상대경로 사용시 매핑은 /join/emil.wow
			data: { "mail":mailInput },		// 파라미터
			success: function (data) {
				alert(data)
			},
			error: function (e) {
				
			}
		})
	}
	
	function fn_idCheck() {
		event.preventDefault();
		// 아작스로 넘겨줄 때, 사용자가 입력한 값을 넘겨줘야함
		var idCheckInput = $("#idCheckInput").val();
		//alert(idCheckInput);
 		$.ajax({
			type: "POST",
			url: "idCheck.wow",			// 상대경로 사용시 매핑은 /join/emil.wow
			data: { "idCheck":idCheckInput },		// 파라미터
			success: function (data) {
				alert(data);
				if(data != "Failed"){
					document.getElementById('btn_Check').style.visibility = 'hidden';
				}else{
					document.getElementById('btn_Check').style.visibility = 'visible';
				}
			},
			error: function (e) {
				
			}
		})
	}
</script>
</html>



