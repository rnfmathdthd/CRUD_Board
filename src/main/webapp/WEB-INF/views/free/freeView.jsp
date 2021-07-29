
<%@page import="com.study.exception.BizNotEffectedException"%>
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
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>자유게시판 - 글 보기</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>


	<c:if test="${e ne null }">
		<div class="alert alert-warning">해당 글이 존재하지 않습니다. 또는 조회수증가
			실패했습니다.</div>
		<a href="freeList.wow" class="btn btn-default btn-sm"> <span
			class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;목록
		</a>
	</c:if>

	<c:if test="${free ne null }">
		<div class="container">
			<div class="page-header">
				<h3>
					자유게시판 - <small>글 보기</small>
				</h3>
			</div>
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<th>글번호</th>
						<td>${free.boNo }</td>
					</tr>
					<tr>
						<th>글제목</th>
						<td>${free.boTitle }</td>
					</tr>
					<tr>
						<th>글분류</th>
						<td>${free.boCategoryNm }</td>
					</tr>
					<tr>
						<th>작성자명</th>
						<td>${free.boWriter }</td>
					</tr>
					<!-- 비밀번호는 보여주지 않음  -->
					<tr>
						<th>내용</th>
						<td><textarea rows="10" name="boContent"
								class="form-control input-sm">
						${free.boContent }
					</textarea></td>
					</tr>
					<tr>
						<th>등록자 IP</th>
						<td>${free.boIp }</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>${free.boHit }</td>
					</tr>
					<tr>
						<th>최근등록일자</th>
						<td>${free.boModDate eq null  ? free.boRegDate : free.boModDate}</td>
					</tr>
					<tr>
						<th>삭제여부</th>
						<td>${free.boDelYn }</td>
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
								<a href="freeEdit.wow?boNo=${free.boNo }"
									class="btn btn-success btn-sm"> <span
									class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									&nbsp;&nbsp;수정
								</a>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- container -->
		<!-- START : 댓글 관련 영역-->
		<div class="container">
			<!-- START : 댓글 입력 영역-->
			<div class="panel panel-default">
				<div class="panel-body form-horizontal">
					<form name="frm_reply"
						action="<c:url value='/reply/replyRegist' />" method="post"
						onclick="return false;">
						<input type="hidden" name="reParentNo" value="${free.boNo}">
						<input type="hidden" name="reCategory" value="FREE">
						<div class="form-group">
							<label class="col-sm-2 control-label">댓글</label>
							<div class="col-sm-8">
								<textarea rows="2" name="reContent" class="form-control"></textarea>
							</div>
							<div class="col-sm-2">
								<button id="btn_reply_regist" type="button"
									class="btn btn-sm btn-info">등록</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- END : 댓글 입력 영역 -->
			<!-- START : 댓글 목록 영역 -->
			<div id="id_reply_list_area"></div>
			<!-- END : 댓글 목록 영역 -->
			<!-- START : 더보기 버튼 영역 -->
			<div class="row text-center" id="id_reply_list_more">
				<button id="btn_reply_list_more">
					<span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
					더보기
				</button>
			</div>
			<!-- END : 더보기 버튼 영역 -->

			<!-- START : 댓글 수정용 Modal -->
			<div class="modal fade" id="id_reply_edit_modal" role="dialog">
				<div class="modal-dialog">
					<!-- Modal content-->
					<div class="modal-content">
						<form name="frm_reply_edit"
							action="<c:url value='/reply/replyModify' />" method="post"
							onclick="return false;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">×</button>
								<h4 class="modal-title">댓글수정</h4>
							</div>
							<div class="modal-body">
								<input type="hidden" name="reNo" value="">
								<textarea rows="3" name="reContent" class="form-control"></textarea>
							</div>
							<div class="modal-footer">
								<button id="btn_reply_modify" type="button"
									class="btn btn-sm btn-info">저장</button>
								<button type="button" class="btn btn-default btn-sm"
									data-dismiss="modal">닫기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- END : 댓글 수정용 Modal -->
		</div>
		<!-- END : 댓글 관련 영역 -->
	</c:if>
</body>

<script type="text/javascript">
<!-- START : 댓글 처리 스크립트-->
/*
	1. javascript 시작시 버튼이벤트 등록, fn_reply_list() 실행
	2. fn_reply_list (로그인 안해도 가능)
	3. 수정, 삭제, 등록 (로그인 해야 가능)
	4. 수정, 삭제, 등록에서 일단 ajax로 reply/** 요청, LoginCheckInterceptor가 가로채서 처리.
	5. 처리 결과에 따라 success 로 갈지 error 로 갈지 결정됨.
	6. 애러로 오는건 로그인 안되어있는거 -> 로그인 화면으로.
 */
	
	var params={"curPage":1, "rowSizePerPage":10, "reCategory":"FREE", "reParentNo":${free.boNo}};

	// 댓글목록을 구하는 함수
	function fn_reply_list() {
	 // fn_reply_list 반복해서 사용, ajax 에 요청해서 db 접근 후 replyVOList 를 가져와서 화면에 출력
		// 새로고침 안하고 바로바로 화면에 적용
		$.ajax({
			type	: 'POST'		// 포스트
			, url	: '<c:url value="/reply/replyList"/>'		// url 요청시 파라미터 정보
			, dataType	: 'JSON'	// Controller 에서 받을 데이터 타입.
			, data 	: 	params
			, success	:	function(data){
				// 댓글 목록영역에 list 개수만큼 댓글 추가
				// 댓글 작성자랑 현재 로그인한 사람이 같을 때만 해당 댓글의 수정, 삭제 버튼
				// 더보기 기능(curPage += 1)
				if(data.result){
					$div = $('#id_reply_list_area');
					$.each(data.data, function(index, elt){ // list 를 반복하는데 index, element
						var str='';
						str += '<div class="row" data-re-no='+ elt.reNo + '>'
							+ '<div class="col-sm-2 text-right">' + elt.reMemId + '</div>'
							+ '<div class="col-sm-6">'
							+ '<pre>' + elt.reContent + '</pre></div>'
							+ '<div class="col-sm-2">'+ elt.reRegDate + '</div>'		// 나중에 mod가 null 이 아니면 reModDate
							+ '<div class="col-sm-2">';
							
							if('${sessionScope.USER_INFO.userId}'==elt.reMemId){
								str += '<button name="btn_reply_edit" type="button"'
									+ 'class="btn btn-sm btn-info">수정</button>'
									+ '<button name="btn_reply_delete" type="button"'
									+ 'class="btn btn-sm btn-danger">삭제</button>';
							}
							str += '</div></div>';
						$div.append(str);
					}); // each
					params.curPage += 1;	// 더보기 버튼 누를시 다음 10개 댓글 나오게
											// 언젠가는 더보기 버튼이 사라져야 함.
					if(data.count < 10){
						$('#id_reply_list_more').remove();
					} // if 
				}
			}
			, error	:	function(req, st, error){
				alert(st);
			}
		});
	}
	$(document).ready(
			function() {
				// 수정버튼 클릭
				$('#id_reply_list_area').on('click', 'button[name=btn_reply_edit]', function(e) {
					// 현재 버튼의 댓글 div 의 내용을 모달창에 복사 글번호 복사
					// 글번호 modal 의 <input> 에 전달>
					$btn=$(this);	// 삭제버튼
					$div=$btn.closest('div.row');
					$modal=$('#id_reply_edit_modal');
					// closest 는 상위를 검색, find는 하위를 검색 (자식의 자손ㄲ지), children 하위검색(바로 자식만)
					$pre=$btn.closest('div.row').find('pre')	// 상위태그중 btn 이랑 가장 가까운 div 중 클래스가 row 인것찾고 그 다음 pre 태그의 내용
					var content = $pre.html();	// html 은 innerHTML값 return, html(' ')은 innerHTML 값 변경
					$textarea=$modal.find('textarea');	
					// modal창 찾고 자식들 중 textarea 찾기
					$textarea.val(content);		// modal 나타나게
					
					var reNo = $div.data('re-no');	// 글 번호
					$modal.find('input[name=reNo]').val(reNo);	 // modal 에서 <input> 태그 찾기 name=reNo인 , 찾은후 값세팅 
					alert(reNo);
					$modal.modal('show');
				}); // btn_reply_edit.click
				// 모달창의 (수정)저장버튼 btn_reply_modify 클릭
				$("#btn_reply_modify").click(function(e) {
					e.preventDefault();
					// reNo, reContent 을 파라미터로 아작스 호출
					// 컨트롤러 단에서 db수정 후 success
					// success 한 후 : 선택(모달내의 reContent, reNo 초기화)
					// 모달 사라지기, 목록 영역에서 업데이트 적용하기(목록영역 제거 후 다시 fn_reply_list)
					$.ajax({
						type : 'POST'
						, url : '<c:url value="/reply/replyModify"/>'
						, data : {'reNo' : $div.data('re-no'), 'reContent' : $modal.find('textarea').val()}
						, dataType : 'JSON'
						, success : function(data){
							console.log(data);
							$modal.find('textarea[name=reContent]').val('');
							$modal.find('input[name=reNo]').val('');
							$modal.modal('hide');
							
							$('#id_reply_list_area').html('');
							params.curPage=1;
							fn_reply_list();
						} //success
						, error : function(req, st, err){
							alert(st);
						} // error
					}); // ajax
					
				}); // btn_reply_modify.click
				// 삭제버튼 클릭
				$('#id_reply_list_area').on('click','button[name=btn_reply_delete]', function(e) {
				// 파라미터로 reNo 넘기고 db 에서 삭제
				// success 해당 버튼의 댓글(div.row)을 목록에서 제거 or 목록 제거후 다시 rn_reply_list
				$btn=$(this);	// 삭제버튼
				$div=$btn.closest('div.row');
				
				if(confirm("글을 삭제하시겠습니까?")){
					$.ajax({
						type : 'POST'
						, url : '<c:url value="/reply/replyDelete"/>'
						, data : {'reNo' : $div.data('re-no')}
						, dataType : 'JSON'
						, success : function(data){
							console.log(data);
							$div.remove();
						} //success
						, error : function(req, st, err){
							
						} // error
					}); // ajax
				}
					
				}); // btn_reply_delete.click

				//더보기 버튼 클릭
				$('#btn_reply_list_more').click(function(e) {
					fn_reply_list();
				}); // #btn_reply_list_more.click
				// 등록버튼 클릭
				$("#btn_reply_regist").click(function(e) {
					// 로그인이 안되어있으면 로그인 화면으로. (interCeptor 와 같이)
					// 등록버튼의 상위 div 찾기, textarea 찾기
					// textarea 내용, reParentNo, reCategory 파라미터로 넘기기 -> 실제 db 에 저장
					
					$btn=$(this); // 자기자신 (등록버튼)
					$form=$btn.closest('form[name=frm_reply]');
					e.preventDefault();
					console.log($form.serialize()); 
					var res = confirm("글 등록 하시겠습니까?")
					if(res){
					// params로 넘겨도 되지만... form 안에 input들을 한번에 보내는 방법
					$.ajax({
						type : 'POST'
						, url : '<c:url value="/reply/replyRegist"/>'
						, data : $form.serialize() // form 태그 안의 reContent, reParentNo, reCategory
						// {"reContent" : value, "reParentNo" : value, "reCategory" : value}
						, dataType :	'JSON'
						, success : function(data){
							console.log(data);
							$form.find('textarea[name=reContent]').val('');	// children 바로 1단계 자식 find 는 자식의 후손들까지
							// 댓글목록영역 등록된 댓글 적용 = 댓글목록 지우고 db에서 다시 받기
							$('#id_reply_list_area').html('');
							params.curPage=1;
							fn_reply_list();
						}	// success
						, error : function(req, st, err){
							window.location.href='<c:url value="/login/login.wow"/>'
						// 로그인 하고 난 후 어떻게 하면 다시 freeView 화면으로 올가? 원랜느 로그인하면 무조건 홈화면.
						}	// error
					});	// ajax
					}
				}); // #btn_reply_regist.click
				// 초기화 함수 호출
				fn_reply_list();
			}); // ready

</script>

</html>






