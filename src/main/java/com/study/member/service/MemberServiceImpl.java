package com.study.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.dao.IMemberDao;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;
@Service
public class MemberServiceImpl implements IMemberService {
	
	@Inject
	IMemberDao memberDao;

	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {
			int totalRowCount = memberDao.getMemberCount(searchVO);
			System.out.println(totalRowCount);
			searchVO.setTotalRowCount(totalRowCount);
			searchVO.pageSetting();
			return memberDao.getMemberList(searchVO);

		}

	

	@Override
	public MemberVO getMember(String memId) throws BizNotFoundException {
			MemberVO member = memberDao.getMember(memId);
			if (member == null)
				throw new BizNotFoundException();
			return member;
		
	}

	@Override
	public void modifyMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException {
			MemberVO vo = memberDao.getMember(member.getMemId());
			if (vo == null)
				throw new BizNotFoundException();
			int cnt = memberDao.updateMember(member);
			if (cnt < 1)
				throw new BizNotEffectedException();
		
	}

	@Override
	public void removeMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException {
			MemberVO vo = memberDao.getMember(member.getMemId());
			if (vo == null)
				throw new BizNotFoundException();
			int cnt = memberDao.deleteMember(member);
			if (cnt < 1)
				throw new BizNotEffectedException();
		
	}

	@Override
	public void registMember(MemberVO member) throws BizDuplicateKeyException, BizNotEffectedException {

			MemberVO vo = memberDao.getMember(member.getMemId());

			if (vo != null)
				throw new BizDuplicateKeyException();
			int cnt = memberDao.insertMember(member);
			if (cnt < 1)
				throw new BizNotEffectedException();
		}
	

}
