<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- paging -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="linkPage" required="true" %>
<%@attribute name="searchVO"  type="com.study.common.vo.PagingVO"%>


<!-- START : 페이지네이션  -->
		<nav class="text-center">
			<ul class="pagination">
				<!-- 첫 페이지  -->
				<li><a href="${linkPage }?curPage=1" data-page="1"><span aria-hidden="true">&laquo;</span></a></li>
				<!-- 이전 페이지 -->
				<c:if test="${searchVO.firstPage ne 1 }">
					<li><a href="${linkPage }?curPage=${searchVO.firstPage-1 }" data-page="${searchVO.firstPage-1 }"><span aria-hidden="true">&lt;</span></a></li>
				</c:if>

				<!-- 페이지 넘버링  -->
				<c:forEach begin="${searchVO.firstPage }" end="${searchVO.lastPage }" step="1" var="i">
					<c:if test="${searchVO.curPage ne i}">
						<li><a href="${linkPage }?curPage=${i }" data-page="${i }">${i }</a></li>
					</c:if>
					<c:if test="${searchVO.curPage eq i }">
						<li class="active"><a href="#">${i }</a></li>
					</c:if>
				</c:forEach>

				<!-- 다음  페이지  -->
				<c:if test="${searchVO.lastPage ne searchVO.totalPageCount }">
					<li><a href="${linkPage }?curPage=${searchVO.lastPage+1 }" data-page="${searchVO.lastPage+1 }"><span aria-hidden="true">&gt;</span></a></li>
				</c:if>

				<!-- 마지막 페이지 -->
				<li><a href="${linkPage }?curPage=${searchVO.totalPageCount }" data-page="${searchVO.totalPageCount }"><span aria-hidden="true">&raquo;</span></a></li>
			</ul>
		</nav>
		<!-- END : 페이지네이션  -->
