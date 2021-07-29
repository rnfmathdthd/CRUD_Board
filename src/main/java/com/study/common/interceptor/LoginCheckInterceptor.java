package com.study.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
		HttpSession session=request.getSession(false);
		//(), (true)는 session이 없으면 새로 생성해서 return, (false)는 session이 없으면 return null
		// session 은 보통 있다.
		// ajax 요청인지, 브라우저 요청인지 구별
		// ajax 요청이고 로그인이 되어있어야만 return true
		String ajax = request.getHeader("X-requested-with");
		
		if(session==null) {
			if(ajax != null) { // session 없고 ajax 일 때
				// 댓글등록(수정, 삭제)에서 올때
				// Referer 저장
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
			response.sendRedirect(request.getContextPath() + "/login/login.wow");
			return false;
		}
		if(session.getAttribute("USER_INFO")==null) {
			if(ajax != null) { // 로그인 안되잇고 ajax 일
				// freeView.jsp 댓글등록(수정, 삭제)에서 올때
				// Referer 저장
				session.setAttribute("PREPAGE", request.getHeader("Referer"));
				// localhost:8080/study4/free/freeView.wow
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
			response.sendRedirect(request.getContextPath() + "/login/login.wow");
			return false;
		
		}
		return true;
	}
}
