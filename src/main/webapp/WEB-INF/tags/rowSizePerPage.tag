<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="aaa" type="com.study.common.vo.PagingVO" %>
<%@ attribute name="linkPage" %>
<%@ attribute name="newForm" %>
<!-- START : 목록건수 및 새글쓰기 버튼  -->
		<div class="row" style="margin-bottom: 10px;">
			<div class="col-sm-3 form-inline">
				전체 ${aaa.totalRowCount }건 조회
				<select id="id_rowSizePerPage" name="rowSizePerPage" class="form-control input-sm">
					<c:forEach var="i" begin="10" end="50" step="10">
						<option value="${i}" ${aaa.rowSizePerPage eq i ? "selected='selected'":""	 }>${i }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-2 col-sm-offset-7 text-right">
				<a href="${linkPage }" class="btn btn-primary btn-sm"> <span class="glyphicon glyphicon-plus-sign" aria-hidden="true">
				</span> &nbsp;${newForm }
				</a>
			</div>
		</div>
		<!-- END : 목록건수 및 새글쓰기 버튼  -->