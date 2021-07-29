package com.study.free.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.study.common.vo.PagingVO;

public class FreeBoardSearchVO extends PagingVO {
	private String searchWord;
	private String searchType;
	private String searchCategory;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public String getSearchWord() {
		return searchWord;
	}
	public String getSearchType() {
		return searchType;
	}
	public String getSearchCategory() {
		return searchCategory;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	
	

}
