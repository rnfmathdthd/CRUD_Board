package com.study.member.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.study.common.vo.PagingVO;

public class MemberSearchVO  extends PagingVO{
	private String searchType;
	private String searchWord;
	private String searchJob;
	private String searchHobby;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public String getSearchType() {
		return searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public String getSearchJob() {
		return searchJob;
	}
	public String getSearchHobby() {
		return searchHobby;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public void setSearchJob(String searchJob) {
		this.searchJob = searchJob;
	}
	public void setSearchHobby(String searchHobby) {
		this.searchHobby = searchHobby;
	}

}
