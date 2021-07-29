package com.study.login.web;

import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.common.util.CookieUtil;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.login.service.ILoginService;
import com.study.login.vo.UserVO;

@Controller
@RequestMapping("login")
public class LogController {

	@Inject
	ILoginService loginService;
	
	// @Controller , Service , Mapper 등의 특정 기능을 하는 빈에는 
	// @Inject 말고 필드(데이터를 저장) 이 있으면 안됩니다.
	// 여러 사용자가 해당 빈(Controller)를 이욯라 때 원치않는 데이터변경이 일어납니다.
	
	@GetMapping("/login.wow")
	public String loginGet() {
		
		return "/login/login";	
	}
	
	@PostMapping("/login.wow")
	public String loginPost(
		 HttpServletRequest req, 
		 HttpServletResponse resp,
		 @RequestParam("userId")String id, 
		 @RequestParam("userPass")String pw,
		 @RequestParam(value="rememberMe", required = false)String save_id
		 )throws Exception {
			if(save_id==null){
				CookieUtil cookieUtils=new CookieUtil(req);
				if(cookieUtils.exists("SAVE_ID")){
					Cookie cookie=CookieUtil.createCookie("SAVE_ID",id,"/",0);
					resp.addCookie(cookie);
				}
				save_id="";
			}
			if(save_id.equals("Y")){
				resp.addCookie(CookieUtil.createCookie("SAVE_ID", id,"/",3600*24*7));
			}
			if((id==null||id.isEmpty() )|| (pw==null||pw.isEmpty())){	
				//redirectPage="03login.jsp?msg="+URLEncoder.encode("입력안함","utf-8");
				return "redirect:/login/login.wow?msg="+URLEncoder.encode("입력안함","utf-8");
			}
			try {
				UserVO user=loginService.loginCheck(id, pw);
				HttpSession session=req.getSession();
				session.setAttribute("USER_INFO",user );
				session.setMaxInactiveInterval(1800);
				
				String prePage = (String) session.getAttribute("PREPAGE");
				session.removeAttribute("PREPAGE");
				if(prePage != null) return "redirect:"+ prePage;
				// 댓글등록에서 login 화면으로 갔을때 login 후 다시 freeView 로 이동
				
				return "redirect:/";
				
			}catch (BizNotFoundException e) {
				return "redirect:/login/login.wow?msg="+URLEncoder.encode("아이디 또는 비번 확인","utf-8");
			}catch (BizPasswordNotMatchedException e) {
				return "redirect:/login/login.wow?msg="+URLEncoder.encode("아이디 또는 비번 확인","utf-8");
			}
		}
	
	@RequestMapping("/logout.wow")
	public String logout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session=req.getSession();
		session.removeAttribute("USER_INFO");
		return "redirect:/login/login.wow?msg="+URLEncoder.encode("로그아웃 되었습니다.","utf-8");
	}
	
}
