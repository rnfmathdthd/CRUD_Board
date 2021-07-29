	package com.study.member.web;

import java.util.List;

import javax.inject.Inject;
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

import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vaild.Modify;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;


@Controller
public class MemberController {
	@Inject
	IMemberService memberService;
	@Inject
	ICommCodeService codeService;
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@ModelAttribute("jobList")
	public List<CodeVO> jobList(){
		List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
		return jobList;
	}
	@ModelAttribute("hobbyList")
	public List<CodeVO> hobbyList(){
		List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
		
		return hobbyList;
	}
	
	
	@RequestMapping(value="/member/memberList.wow", method = {RequestMethod.GET, RequestMethod.POST})
	public String memberList(@ModelAttribute("searchVO")MemberSearchVO searchVO ,Model model) {
			
		model.addAttribute("searchVO", searchVO);
		
		List<MemberVO> memberList=memberService.getMemberList(searchVO);
		model.addAttribute("memberList", memberList);
		logger.info("{}", searchVO);
		return "member/memberList";
	}
	@RequestMapping(value="/member/memberView.wow", method = {RequestMethod.GET, RequestMethod.POST})
	public String memberView(@RequestParam("memId")String memId,Model model) {
			
		
		try {
			MemberVO member = memberService.getMember(memId);
			model.addAttribute("member", member);
		} catch (BizNotFoundException e) {
			
			model.addAttribute("e", e);
		}
		logger.info("{}", model);
		return "member/memberView";
	}
	@RequestMapping(value="/member/memberEdit.wow", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView memberEdit(@RequestParam("memId")String memId) {
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
	
	@RequestMapping(value="/member/memberModify.wow", method = {RequestMethod.GET, RequestMethod.POST})
	public String memberModify(@ModelAttribute("member")@Validated({Default.class, Modify.class})MemberVO member,
			BindingResult errors, 
			Model model) {
		if(errors.hasErrors()) {
			return "/member/memberEdit";
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
@RequestMapping(value="/member/memberForm.wow", method = {RequestMethod.GET, RequestMethod.POST})	
public void memberForm(Model model) {

	}
@RequestMapping(value="/member/memberRegist.wow",method = {RequestMethod.GET, RequestMethod.POST})
public String memberRegist(@ModelAttribute("member")@Validated({Default.class, Modify.class})MemberVO member,
		BindingResult errors, 
		Model model) {
	if(errors.hasErrors()) {
		return "/member/memberForm";
	}
	ResultMessageVO resultMessageVO=new ResultMessageVO();
	
	try {
		memberService.registMember(member);
		resultMessageVO.setResult(true);
		resultMessageVO.setMessage("등록 성공");
		resultMessageVO.setTitle("등록");
		resultMessageVO.setUrl("/member/memberList.wow");
	} catch (BizDuplicateKeyException edk) {
		resultMessageVO.setResult(false);
		resultMessageVO.setMessage("등록 실패 아이디겹친다");
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
	
	logger.info("{}", member);
	return "/common/message";
	}
@RequestMapping(value="/member/memberDelete", method = {RequestMethod.GET, RequestMethod.POST})
public String memberDelete(@ModelAttribute("member")MemberVO member ,Model model) {
	
		ResultMessageVO resultMessageVO=new ResultMessageVO();
		try{
			memberService.removeMember(member);
			resultMessageVO.setResult(true);
			resultMessageVO.setMessage("삭제 성공");
			resultMessageVO.setTitle("삭제");
			resultMessageVO.setUrl("/member/memberList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		}catch (BizNotEffectedException eee){
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("삭제 실패 notEffected");
			resultMessageVO.setTitle("삭제");
			resultMessageVO.setUrl("/member/memberList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		}catch (BizNotFoundException efe){
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("삭제 실패 notFound");
			resultMessageVO.setTitle("삭제");
			resultMessageVO.setUrl("/member/memberList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		}
	model.addAttribute("resultMessageVO", resultMessageVO);
	
	logger.info("{}", member);
	return "/common/message";
	}
	
}
