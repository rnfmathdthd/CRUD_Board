package com.study.member.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vaild.IStep1;
import com.study.common.vaild.IStep2;
import com.study.common.vaild.IStep3;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.member.dao.IMemberDao;
import com.study.member.service.IMemberService;
import com.study.member.service.MailSendService;
import com.study.member.vo.MemberVO;

@Controller
@SessionAttributes("member")		// session에 model을 저장
public class JoinController {
	@ModelAttribute("member")
	public MemberVO member() {	// model에 이미 member가 있으면 실행 안됨, session에 model을 담아서 계속 사용하려는 용도
		MemberVO member = new MemberVO();
		return member;
	}
	
	@Inject
	ICommCodeService codeService;
	
	@Inject
	IMemberService memberService;
	
	@ModelAttribute("jobList")
	public List<CodeVO> jobList() {
		List<CodeVO> jobList=codeService.getCodeListByParent("JB00");
		return jobList;
	}
	
	@ModelAttribute("hobbyList")
	public List<CodeVO> hobbyList() {
		List<CodeVO> hobbyList=codeService.getCodeListByParent("HB00");
		return hobbyList;
	}
	
	// @ModelAttribute는 메서드 실행 시 마다 member객체를 새로 생성 후
	// 파라미터로 넘어온 값들 셋팅, model에 담는다
	@RequestMapping("/join/step1.wow")
	public String step1(@ModelAttribute("member") MemberVO member) {
		
		return "join/step1";
	}
	
	@RequestMapping("/join/step2.wow")
	public String step2(@ModelAttribute("member") @Validated({IStep1.class}) MemberVO member, BindingResult errors) {
		if (errors.hasErrors()) {
			return "join/step1";
		}
		return "join/step2";
	}
	
	@RequestMapping("/join/step3.wow")
	public String step3(@ModelAttribute("member") @Validated({IStep2.class}) MemberVO member, BindingResult errors) {
		if (errors.hasErrors()) {
			return "join/step2";
		}
		return "join/step3";
	}
	
	@RequestMapping("/join/regist.wow")
	public String regist(@ModelAttribute("member") @Validated({IStep3.class}) MemberVO member, BindingResult errors, Model model, SessionStatus sessionStatues) {
		if (errors.hasErrors()) {
			return "join/step3";
		}
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		try {
			sessionStatues.setComplete();
			memberService.registMember(member);
			resultMessageVO.setResult(true);
			resultMessageVO.setMessage("등록 성공");
			resultMessageVO.setTitle("등록");
			resultMessageVO.setUrl("/member/memberList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		} catch (BizDuplicateKeyException edk) {
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("등록 실패 DuplicateKey");
			resultMessageVO.setTitle("등록");
			resultMessageVO.setUrl("/member/memberList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		} catch (BizNotEffectedException eee) {
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("등록 실패 notEffected");
			resultMessageVO.setTitle("등록");
			resultMessageVO.setUrl("/member/memberList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		
		return "common/message";
	}
	
	@RequestMapping("/join/cancel")
	public String cancel(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/";
	}
	
	@Inject
	MailSendService mss;
	
	@RequestMapping("/join/mail.wow")
	@ResponseBody	// ajax에서 데이터 받을때 return값을 view 찾는것이 아니라 ajax의 success함수의 매개변수로
	public String mail(@RequestParam String mail) {
		String random = mss.sendAuthMail(mail);
		return random;
	}
	
	@Inject
	IMemberDao memberDao;
	
	@RequestMapping("/join/idCheck.wow")
	@ResponseBody	// ajax에서 데이터 받을때 return값을 view 찾는것이 아니라 ajax의 success함수의 매개변수로
	public String idCheck(@RequestParam String idCheck) {
		MemberVO vo = memberDao.getMember(idCheck);
		String result;
		if (vo != null) {
			result = "Failed";
		}else {
			result = "Good id";
		}
		return result;
	}
	
}
