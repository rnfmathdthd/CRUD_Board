package com.study.free.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.study.common.vaild.Modify;


public class FreeBoardVO {

	@NotNull(groups = Modify.class)
	private int boNo; /* 글 번호 */
	
	@NotBlank(message = "글 제목 채워줘요")
	private String boTitle; /* 글 제목 */
	
	@NotBlank(message = "글 분류 선택해야합니다.")
	private String boCategory; /* 글 분류 코드 */
	
	@NotBlank(message = "작성자 입력은 필수입니다.")
	private String boWriter; /* 작성자명 */
//	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*/d)[A-Za-z/d]{4,}$")
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String boPass; /* 비밀번호 */
	
	@NotBlank(message = "글 내용을 입력해주세요")
	private String boContent; /* 글 내용 */
	private String boIp; /* 등록자 IP */
	private int boHit; /* 조회수 */
	private String boRegDate; /* 등록 일자 */
	private String boModDate; /* 수정 일자 */
	private String boDelYn; /* 삭제 여부 */
	
	private String boCategoryNm;
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getBoNo() {
		return boNo;
	}

	public String getBoTitle() {
		return boTitle;
	}

	public String getBoCategory() {
		return boCategory;
	}

	public String getBoWriter() {
		return boWriter;
	}

	public String getBoPass() {
		return boPass;
	}

	public String getBoContent() {
		return boContent;
	}

	public String getBoIp() {
		return boIp;
	}

	public int getBoHit() {
		return boHit;
	}

	public String getBoRegDate() {
		return boRegDate;
	}

	public String getBoModDate() {
		return boModDate;
	}

	public String getBoDelYn() {
		return boDelYn;
	}

	public void setBoNo(int boNo) {
		this.boNo = boNo;
	}

	public void setBoTitle(String boTitle) {
		this.boTitle = boTitle;
	}

	public void setBoCategory(String boCategory) {
		this.boCategory = boCategory;
	}

	public void setBoWriter(String boWriter) {
		this.boWriter = boWriter;
	}

	public void setBoPass(String boPass) {
		this.boPass = boPass;
	}

	public void setBoContent(String boContent) {
		this.boContent = boContent;
	}

	public void setBoIp(String boIp) {
		this.boIp = boIp;
	}

	public void setBoHit(int boHit) {
		this.boHit = boHit;
	}

	public void setBoRegDate(String boRegDate) {
		this.boRegDate = boRegDate;
	}

	public void setBoModDate(String boModDate) {
		this.boModDate = boModDate;
	}

	public void setBoDelYn(String boDelYn) {
		this.boDelYn = boDelYn;
	}

	public String getBoCategoryNm() {
		return boCategoryNm;
	}

	public void setBoCategoryNm(String boCategoryNm) {
		this.boCategoryNm = boCategoryNm;
	}

	
	
}
