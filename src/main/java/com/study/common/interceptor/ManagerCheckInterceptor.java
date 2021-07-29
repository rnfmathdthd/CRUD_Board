package com.study.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.study.login.vo.UserVO;

public class ManagerCheckInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
		HttpSession session=request.getSession(false);
		//userRole 세팅이 member인지 manager인지
		
		if(session==null) {
			response.sendRedirect(request.getContextPath() + "/login/login.wow");
			return false;
		}
		//session not null
	
		UserVO user=(UserVO)session.getAttribute("USER_INFO");
		if(user==null) {
			response.sendRedirect(request.getContextPath()+"/login/login.wow");
			return false;
		}
		if(!(user.getUserRole().contains("MANAGER"))) {//로그인했는데 일반 멤버
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "매니저도 아닌게 까불어");
			return false;
		}
		return true;
	}
}
