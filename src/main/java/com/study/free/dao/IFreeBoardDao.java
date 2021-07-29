package com.study.free.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;
@Mapper
public interface IFreeBoardDao {
	public int getBoardCount(FreeBoardSearchVO searchVO);
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO);
	public FreeBoardVO getBoard(int boNo);
	public int increaseHit(int boNo);
	public int updateBoard(FreeBoardVO free);
	public int deleteBoard(FreeBoardVO free);
	public int insertBoard(FreeBoardVO free);
}
