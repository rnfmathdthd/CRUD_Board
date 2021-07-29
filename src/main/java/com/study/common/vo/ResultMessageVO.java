package com.study.common.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResultMessageVO {
	private boolean result;
	private String message;
	private String title;
	private String url;
	private String urlTitle;
	
	@Override
	public String toString() {
		return ToStringBuilder
				.reflectionToString
				(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public boolean isResult() {
		return result;
	}
	public String getMessage() {
		return message;
	}
	public String getTitle() {
		return title;
	}
	public String getUrl() {
		return url;
	}
	public String getUrlTitle() {
		return urlTitle;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}
	
	

}
