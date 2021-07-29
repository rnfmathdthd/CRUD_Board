package com.study.free.web;


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
import com.study.common.vaild.Regist;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;


@Controller  //FreeController를 빈으로 등록하려 하는데
				// codeService, freeService 주입해야 빈으로 생성가능
				// 주입을 하려면 freeService가 빈으로 등록되어있어야함
@RequestMapping("/free")
public class FreeController {
	@Inject
	ICommCodeService codeService;
	@Inject
	IFreeBoardService freeService;
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	// form, edit에서 codeService 사용합니다.
	// 이 메소드에서 return한 값은 공통적으로 model에 담김
	@ModelAttribute("cateList")
	public List<CodeVO> cateList(){
		List<CodeVO> cateList = codeService.getCodeListByParent("BC00");
		return cateList;
	}
	
	@RequestMapping("/freeList.wow")
	public String freeList(@ModelAttribute("searchVO")FreeBoardSearchVO searchVO ,Model model) {
	
		
		//자동으로 searchVo객체 만들고 파라미터 세팅
		model.addAttribute("searchVO", searchVO);
		
		List<FreeBoardVO> freeList=freeService.getBoardList(searchVO);
		model.addAttribute("freeList", freeList);		
		logger.info("{}", searchVO);
		return "free/freeList";
	}
	@RequestMapping(value="/freeView.wow", method = RequestMethod.GET) 
	public String freeView(@RequestParam(value="boNo", required= true)int boNo,Model model) {
		//단 파라미터이름이랑 변수이름이랑 맞춰줘야함
		//파라미터이름이랑 변수이름이랑 다르게 쓰고 싶으면 public String freeView(@RequestParam("변수명")파라미터 타입 받는 파라미터명,Model model) 
		
		try {
			freeService.increaseHit(boNo);
			FreeBoardVO free = freeService.getBoard(boNo);
			model.addAttribute("free", free);
		} catch (BizNotFoundException | BizNotEffectedException e) {
			model.addAttribute("e", e);
		}
		
		logger.info("{}", model);
		return "free/freeView";
	}
	@RequestMapping("/freeEdit.wow")
	public ModelAndView freeEdit(@RequestParam("boNo")int boNo) {
		ModelAndView mav = new ModelAndView();
		
		try {
			FreeBoardVO free = freeService.getBoard(boNo);
			mav.addObject("free", free);
		} catch (BizNotFoundException e) {
			mav.addObject("e", e);
		}
		
		
		logger.info("{}", boNo);
		return mav;
	}
	@RequestMapping("/freeModify.wow")
	//검사받는 객체 = command 객체(free)
	//검사한결과 BindingResult는 command 바로 뒤에
	
	public String freeModify(@ModelAttribute("free")@Validated({Default.class, Modify.class})FreeBoardVO free, 
			BindingResult errors,
			Model model) {
		if(errors.hasErrors()) {
			return "/free/freeEdit";
		}
		ResultMessageVO resultMessageVO=new ResultMessageVO();
		
		try {
			freeService.modifyBoard(free);
			resultMessageVO.setResult(true);
			resultMessageVO.setMessage("수정 성공");
			resultMessageVO.setTitle("수정");
			resultMessageVO.setUrl("/free/freeList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		} catch (BizNotFoundException enf) {
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("수정 실패 notFound");
			resultMessageVO.setTitle("수정");
			resultMessageVO.setUrl("/free/freeList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		} catch (BizNotEffectedException ene) {
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("수정 실패 notEffected");
			resultMessageVO.setTitle("수정");
			resultMessageVO.setUrl("/free/freeList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		} catch (BizPasswordNotMatchedException epn) {
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("수정 실패 비밀번호틀려");
			resultMessageVO.setTitle("수정");
			resultMessageVO.setUrl("/free/freeList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		}
		
		model.addAttribute("resultMessageVO", resultMessageVO);
	
	
		logger.info("{}", free);
		return "/common/message";
	}
@RequestMapping("/freeForm.wow")	
public String freeForm(@ModelAttribute("free")FreeBoardVO free, Model model) {
	return "/free/freeForm";
	}
@RequestMapping("/freeRegist.wow")
public String freeRegist(@ModelAttribute("free")@Validated({Default.class, Regist.class})
		FreeBoardVO free, 
		BindingResult errors,
		Model model) {
	if(errors.hasErrors()) {
		return "/free/freeForm";
	}
	
	ResultMessageVO resultMessageVO=new ResultMessageVO();
	try {
		freeService.registBoard(free);
		resultMessageVO.setResult(true);
		resultMessageVO.setMessage("등록 성공");
		resultMessageVO.setTitle("등록");
		resultMessageVO.setUrl("/free/freeList.wow");
		resultMessageVO.setUrlTitle("목록으로");
	} catch (BizNotEffectedException e) {
		resultMessageVO.setResult(false);
		resultMessageVO.setMessage("등록 실패 notEffected");
		resultMessageVO.setTitle("등록");
		resultMessageVO.setUrl("/free/freeList.wow");
		resultMessageVO.setUrlTitle("목록으로");
	}
	model.addAttribute("resultMessageVO", resultMessageVO);
	logger.info("{}", free);
	return "/common/message";
	}
@RequestMapping("/freeDelete.wow")
public String freeDelete(@ModelAttribute("free")FreeBoardVO free ,Model model) {
	
		ResultMessageVO resultMessageVO=new ResultMessageVO();
		try{
			freeService.removeBoard(free);
			//성공한 경우
			resultMessageVO.setResult(true);
			resultMessageVO.setMessage("삭제 성공");
			resultMessageVO.setTitle("삭제");
			resultMessageVO.setUrl("/free/freeList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		}catch (BizNotFoundException enf){
			//실패 BizNotFoundException
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("삭제 실패 notFound");
			resultMessageVO.setTitle("삭제");
			resultMessageVO.setUrl("/free/freeList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		}catch(BizNotEffectedException ene){
			//실패 BizNotEffectedException
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("삭제 실패 notEffected");
			resultMessageVO.setTitle("삭제");
			resultMessageVO.setUrl("/free/freeList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		}catch (BizPasswordNotMatchedException epn){
			//실패 BizPasswordNotMatchedException
			resultMessageVO.setResult(false);
			resultMessageVO.setMessage("삭제 실패 password안맞음");
			resultMessageVO.setTitle("삭제");
			resultMessageVO.setUrl("/free/freeList.wow");
			resultMessageVO.setUrlTitle("목록으로");
		}
	model.addAttribute("resultMessageVO", resultMessageVO);
	logger.info("{}", free);
	return "/common/message";
	}
}
