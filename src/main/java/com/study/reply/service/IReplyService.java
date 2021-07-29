package com.study.reply.service;

import java.util.List;

import com.study.exception.BizAccessFailException;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;
import com.study.reply.vo.ReplySearchVO;
import com.study.reply.vo.ReplyVO;


public interface IReplyService {
	
	public List<ReplyVO> getReplyListByParent(ReplySearchVO searchVO);
	
	public void registReply(ReplyVO reply);
	
	public void modifyReply(ReplyVO reply) throws BizNotFoundException, BizAccessFailException;
	
	public void removeReply(ReplyVO reply) throws BizNotFoundException, BizAccessFailException;
	
}
