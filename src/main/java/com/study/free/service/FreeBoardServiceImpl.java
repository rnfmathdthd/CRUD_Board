package com.study.free.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

@Service
public class FreeBoardServiceImpl implements IFreeBoardService {

	@Inject
	IFreeBoardDao freeDao;

	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) {
		
			int totalRowCount = freeDao.getBoardCount(searchVO);
			System.out.println(totalRowCount);
			searchVO.setTotalRowCount(totalRowCount);
			searchVO.pageSetting();
			return freeDao.getBoardList(searchVO);
		}
	

	@Override
	public FreeBoardVO getBoard(int boNo) throws BizNotFoundException {
	
			FreeBoardVO free = freeDao.getBoard(boNo);
			if (free == null)
				throw new BizNotFoundException();
			return free;
		}
	

	@Override
	public void increaseHit(int boNo) throws BizNotEffectedException {
			int cnt = freeDao.increaseHit(boNo);
			if (cnt < 1)
				throw new BizNotEffectedException();
		}
	

	@Override
	public void modifyBoard(FreeBoardVO free)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
			FreeBoardVO vo = freeDao.getBoard(free.getBoNo());
			if (vo == null)
				throw new BizNotFoundException(); // 이거 발생 확률은 거의없다.
			// vo는 db에 있는 데이터 , free는 edit에서 넘어온 데이터
			if (vo.getBoPass().equals(free.getBoPass())) {
				int cnt = freeDao.updateBoard(free);
				if (cnt < 1)
					throw new BizNotEffectedException();
			} else {
				throw new BizPasswordNotMatchedException();
			}
		}
	

	@Override
	public void removeBoard(FreeBoardVO free)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
	
			FreeBoardVO vo = freeDao.getBoard(free.getBoNo());
			if (vo == null)
				throw new BizNotFoundException(); // 이거 발생 확률은 거의없다.
			// vo는 db에 있는 데이터 , free는 edit에서 넘어온 데이터
			if (vo.getBoPass().equals(free.getBoPass())) {
				int cnt = freeDao.deleteBoard(free);
				if (cnt < 1)
					throw new BizNotEffectedException();
			} else {
				throw new BizPasswordNotMatchedException();
			}
		}
	

	@Override
	public void registBoard(FreeBoardVO free) throws BizNotEffectedException {
	
			int cnt = freeDao.insertBoard(free);
			if (cnt < 1)
				throw new BizNotEffectedException();
		}
	

}
