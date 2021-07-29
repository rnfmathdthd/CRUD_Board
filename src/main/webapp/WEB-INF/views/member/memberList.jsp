<%@page import="com.study.code.service.CommCodeServiceImpl"%>
<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.service.ICommCodeService"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="hn" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/inc/header.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp"%>
<%
request.setCharacterEncoding("utf-8");
%>


${searchVO }
 <div class="container">	
 
 	<!-- START : 검색 폼  -->
		<div class="panel panel-default collapse in" id="id_search_area">
			<div class="panel-body">
				<form name="search" action="memberList.wow" method="post" class="form-horizontal">
					<input type="hidden" name="curPage" value="${searchVO.curPage }"> <input type="hidden" name="rowSizePerPage" value="${searchVO.rowSizePerPage }">
					<div class="form-group">
						<label for="id_searchType" class="col-sm-2 control-label">검색</label>
						<div class="col-sm-2">
							<select id="id_searchType" name="searchType" class="form-control input-sm">
								<option value="NM" ${searchVO.searchType eq "NM" ? "selected='selected'" : "" } >이름</option>
								<option value="ID" ${searchVO.searchType eq "ID" ? "selected='selected'" : "" } >아이디</option>
								<option value="HP"  ${searchVO.searchType eq "HP" ? "selected='selected'" : "" } >핸드폰번호</option>
							</select>
						</div>
						<div class="col-sm-2">
							<input type="text" name="searchWord" class="form-control input-sm" value="${searchVO.searchWord }" placeholder="검색어">
						</div>
						<div>
							<label for="id_searchJob" class="col-sm-1 col-sm-offset control-label">직업</label>
							<div class="col-sm-2">
								<select id="id_searchJob" name="searchJob" class="form-control input-sm">
									<option value="">-- 전체 --</option>
									<c:forEach items="${jobList}" var="code">
										<option value="${code.commCd}" ${searchVO.searchJob eq code.commCd ? "selected='selected'" : "" } >${code.commNm}</option>
									</c:forEach>
								</select>
							</div>
							<label for="id_searchHobby" class="col-sm-1 col-sm-offset control-label">취미</label>
							<div class="col-sm-2">
								<select id="id_search" name="searchHobby" class="form-control input-sm">
									<option value="">-- 전체 --</option>
									<c:forEach items="${hobbyList}" var="code">
										<option value="${code.commCd}" ${searchVO.searchHobby eq code.commCd ? "selected='selected'" : "" } >${code.commNm}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-9 text-right">
							<button type="button" id="id_btn_reset" class="btn btn-sm btn-default">
								<i class="fa fa-sync"></i> &nbsp;&nbsp;초기화
							</button>
						</div>
						<div class="col-sm-1 text-right">
							<button type="submit" class="btn btn-sm btn-primary ">
								<i class="fa fa-search"></i> &nbsp;&nbsp;검 색
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- END : 검색 폼  -->


	<hn:rowSizePerPage aaa="${searchVO }" linkPage="memberForm.wow" newForm="회원등록"/>		
 
 
	<h3>회원목록</h3>		
	<table class="table table-striped table-bordered">
	<caption class="hidden">회원목록 조회</caption>
	<colgroup>
		<col style="width: 15%" />
		<col />
		<col style="width: 20%" />
		<col style="width: 20%" />
		<col style="width: 15%" />
		<col style="width: 15%" />
	</colgroup>
	<thead>
		<tr>
			<th>ID</th>
			<th>회원명</th>
			<th>HP</th>
			<th>생일</th>
			<th>직업</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${memberList }" var="member">
	 			<tr>
				<td>${member.memId }</td>
				<td><a href="memberView.wow?memId=${member.memId }">
				${member.memName }</a></td>
				<td>${member.memHp }</td>
				<td>${member.memHp }</td>
				<td>${member.memJobNm }</td>
				<td>${member.memMileage }</td>
			</tr>
	 	</c:forEach>
	</tbody>			
	</table>
	
	
	<hn:paging linkPage="memberList.wow" searchVO="${searchVO }"></hn:paging>
	
</div>

</body>

<script type="text/javascript">
		// 변수 정의
		var f = document.forms['search'];

		// 함수 정의

		// 각 이벤트 등록
		// 페이지 링크 클릭
		$('ul.pagination li a[data-page]').click(function(e) {
			e.preventDefault(); // 이벤트 전파 방지,  <a>의 기본 클릭이벤트를 막기위함
			// data-page에 있는 값을 f.curPage.value에 설정, 서브밋
			f.curPage.value = $(this).data('page');
			f.submit();
		}); // ul.pagination li a[data-page]

		// 검색 버튼 클릭
		$(f).submit(function(e) {
			e.preventDefault();
			f.curPage.value = 1;
			f.submit();
		}); // f.submit

		// 목록 갯수 변경
		// id_rowSizePerPage 변경되었을 때
		// 페이지 1, 목록수 = 현재값 으로 변경 후 서브밋
		$('#id_rowSizePerPage').change(function(e) {
			f.curPage.value = 1;
			f.rowSizePerPage.value = this.value;
			f.submit();
		}); // '#id_rowSizePerPage'.change

		// 초기화 버튼 클릭
		$('#id_btn_reset').click(function() {
			f.curPage.value = 1;
			f.searchWord.value = "";
			f.searchType.options[0].selected = true;
			f.searchCategory.options[0].selected = true;
		}); // #id_btn_reset.click

		// jQuery에서 클래스 삭제 및 추가, 내용 변경
		$('#id_search_area').on(
				'shown.bs.collapse',
				function() {
					$('#searchTap').removeClass('fa-chevron-down').addClass(
							'fa-chevron-up');
					$('#id_seach_show span').html('&nbsp; 검색열기');
				});

		$('#id_search_area').on(
				'hidden.bs.collapse',
				function() {
					$('#searchTap').removeClass('fa-chevron-up').addClass(
							'fa-chevron-down');
					$('#id_seach_show span').html('&nbsp; 검색열기');
				});
	</script>
</html>