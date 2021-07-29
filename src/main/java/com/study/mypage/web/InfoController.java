package com.study.mypage.web;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.study.common.vaild.Modify;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.member.service.IMemberService;
import com.study.member.vo.MemberVO;

@Controller
@RequestMapping("/mypage")
public class InfoController{
	
	@Inject
	IMemberService memberService;
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/info.wow")
	public String myPage(
			HttpSession session , 
			Model model
			) throws Exception { 
				
		UserVO user = (UserVO)session.getAttribute("USER_INFO");
		logger.debug("user={}", user);
		
		try{
			MemberVO member=memberService.getMember(user.getUserId());
			model.addAttribute("member", member); // model은 get, setAttribute
		}catch (BizNotFoundException e) {
			return "redirect:/";
		}
		
		return "/mypage/info";
	}
	
	@RequestMapping(value="/edit.wow", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView edit(@RequestParam("memId")String memId) {
		ModelAndView mav = new ModelAndView();
		
		
		try {
			MemberVO member = memberService.getMember(memId);
			mav.addObject("member", member);
		} catch (BizNotFoundException e) {
			mav.addObject("e", e);
		}
		logger.info("{}", memId);
		return mav;
	}
	
	@RequestMapping(value="/modify.wow", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute("member")@Validated({Default.class, Modify.class})MemberVO member,
			BindingResult errors, 
			Model model) {
		if(errors.hasErrors()) {
			return "/mypage/edit";
		}
		ResultMessageVO resultMessageVO=new ResultMessageVO();
	
		try {
			memberService.modifyMember(member);
			resultMessageVO.setResult(true);
			resultMessageVO.setMessage("수정 성공");
			resultMessageVO.setTitle("수정");
			resultMessageVO.setUrl("/member/memberView.wow?memId="+member.getMemId());
			resultMessageVO.setUrlTitle("해당뷰");
		} catch (BizNotEffectedException eee) {
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("수정 실패 notEffected");
			resultMessageVO.setTitle("수정");
			resultMessageVO.setUrl("/member/memberList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		} catch (BizNotFoundException efe) {
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("수정 실패 notFound");
			resultMessageVO.setTitle("수정");
			resultMessageVO.setUrl("/member/memberList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
	
	
		logger.info("{}", member);
		return "/common/message";
	}
	@RequestMapping("/delete.wow")
	public String delete(MemberVO member, Model model) {
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		try {
			memberService.removeMember(member);
			resultMessageVO.setResult(true);
			resultMessageVO.setMessage("삭제 성공");
			resultMessageVO.setTitle("삭제");
			resultMessageVO.setUrl("/member/memberList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		} catch (BizNotEffectedException eee) {
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("삭제 실패 notEffected");
			resultMessageVO.setTitle("삭제");
			resultMessageVO.setUrl("/member/memberList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		} catch (BizNotFoundException efe) {
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("삭제 실패 notFound");
			resultMessageVO.setTitle("삭제");
			resultMessageVO.setUrl("");
			resultMessageVO.setUrlTitle("목록으로");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}
}
