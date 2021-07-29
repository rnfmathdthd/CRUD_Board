<%@tag import="java.text.DecimalFormat"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="pagingVO" required="true" type="com.study.common.vo.PagingVO"  %>
<%@ attribute name="linkPage" required="true" type="java.lang.String" %>

<!-- type : pagingVO라 했는데   
	         jsp에서는 FreeBoardSearchVO를 넘겨줬는데
	          형변환 되서 searchWord,searchType 을 못 쓰는거 아니냐?? 
 	tag에서는 형변환 되는게 아니라 그냥 type만 명시해주는거다. 
 	'pagingVO 또는 그 자식들만 올 수있다'는걸 명시, 형변환 x
 -->
 <!-- linkpage랑 pagingVO 값만 출력 -->
 
 linkPage : ${linkPage }   <br>
 pagingVO : {pagingVO}     <br> 
 
 
 

