
<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.service.CommCodeServiceImpl"%>
<%@page import="com.study.code.service.ICommCodeService"%>
<%@page import="com.study.exception.BizNotFoundException"%>
<%@page import="com.study.free.service.FreeBoardServiceImpl"%>
<%@page import="com.study.free.service.IFreeBoardService"%>
<%@page import="com.study.free.vo.FreeBoardVO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	
	<c:if test="${e ne null }">
		<div class="alert alert-warning">해당 글이 존재하지 않습니다.</div>
		<a href="freeList.wow" class="btn btn-default btn-sm"> <span
			class="glyphicon glyphicon-list" aria-hidden="true"> </span> &nbsp;목록
		</a>
	</c:if>
	<c:if test="${free ne null }">
		<div class="container">
			<div class="page-header">
				<h3>
					자유게시판 - <small>글 수정</small>
				</h3>
			</div>
		
			<form:form action="freeModify.wow" method="post" modelAttribute="free">
				<table class="table table-striped table-bordered">
					<colgroup>
						<col width="20%" />
						<col />
					</colgroup>
					<tr>
						<th>글번호</th>
						<td>${free.boNo }<input type="hidden" name="boNo"
							value="${free.boNo }">
							<form:hidden path="boNo"/>
							<form:errors path="boNo"/>
						</td>

					</tr>
					<tr>
						<th>제목</th>
						
						<td><form:input path="boTitle" cssClass="form-control input-sm" /></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${free.boWriter }<form:hidden path="boWriter"/>
						<form:errors path="boWriter"/>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						
						<td>
						<form:password path="boPass" cssClass="form-control input-sm"/>
						<span class="text-danger"> <span
								class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						</span>
								<form:errors path="boPass"/>
						</td>
					</tr>
					<tr>
						<th>분류</th>
						<td>
						<form:select path="boCategory">
						<form:option value="">--선택하세요--</form:option>
						<form:options items="${cateList }" itemLabel="commNm" itemValue="commCd"/>
						</form:select>
						<form:errors path="boCategory"/>
						<%-- <select name="boCategory" class="form-control input-sm"
							required="required">
								<option value="">-- 선택하세요--</option>
								<c:forEach items="${cateList }" var="cate">
									<option value="${cate.commCd }"
										${free.boCategory eq cate.commCd  ? "selected='selected'" : ""}>${cate.commOrd}.${cate.commNm }</option>
								</c:forEach>
						</select></td> --%>
					</tr>
					<tr>
						<th>내용</th>
						<td>	 <form:textarea path="boContent"
								 	cssClass="form-control input-sm"/>
								 		<form:errors path="boContent"/>
								 </td>
					</tr>
					<tr>
						<th>IP</th>
						<td>${free.boIp }</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>${free.boHit }</td>
					</tr>
					<tr>
						<th>최근등록일자</th>
						<td>${free.boModDate eq null ? free.boRegDate : free.boModDate }
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="pull-left">
								<a href="freeList.wow" class="btn btn-default btn-sm"> <span
									class="glyphicon glyphicon-list" aria-hidden="true"></span>
									&nbsp;&nbsp;목록
								</a>
							</div>
							<div class="pull-right">

								<a href="freeList.wow" class="btn btn-info btn-sm"> <span
									class="glyphicon glyphicon-list" aria-hidden="true"></span>
									&nbsp;목록으로
								</a>
								<button type="submit" class="btn btn-sm btn-primary">
									<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
									&nbsp;&nbsp;저장
								</button>

								<button type="submit" formaction="freeDelete.wow"
									class="btn btn-sm btn-danger">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									&nbsp;&nbsp;삭제
								</button>


							</div>
						</td>
					</tr>
				</table>
			</form:form>

		</div>
		<!-- container -->
	</c:if>
</body>
</html>


