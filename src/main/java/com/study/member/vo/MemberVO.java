package com.study.member.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.study.common.vaild.IStep1;
import com.study.common.vaild.IStep2;
import com.study.common.vaild.IStep3;
import com.study.common.vaild.Modify;

public class MemberVO {
	@NotBlank(message = "id는 필수", groups = {Modify.class, IStep2.class})
	private String memId;              /* 회원아이디 */
	@NotBlank(message = "비번 필수", groups = {Modify.class, IStep2.class})
	private String memPass;            /* 회원비밀번호 */
	@NotBlank(message = "이름 필수", groups = {Modify.class, IStep2.class})
	private String memName;            /* 회원이름 */
	private String memBir;             /* 회원생년월일 */
	
	
	@NotBlank(message = "우편번호필수", groups = {Modify.class, IStep3.class})
	private String memZip;             /* 회원우편번호 */
	@NotBlank(message = "주소는 필수", groups = {Modify.class, IStep3.class})
	private String memAdd1;            /* 회원주소 */
	@NotBlank(message = "주소는 필수", groups = {Modify.class, IStep3.class})
	private String memAdd2;            /* 회원상세주소 */
	@NotBlank(message = "전화번호는 필수", groups = {Modify.class, IStep3.class})
	private String memHp;              /* 회원전화번호 */
	
	@Email(message = "Email형식이아닙니다.", groups = {Modify.class, IStep2.class})
	private String memMail;            /* 회원이메일 */
	@Size(max = 4, min = 4, message = "다시", groups = {Modify.class, IStep3.class})
	private String memJob;             /* 회원직업 */
	@Size(max = 4, min = 4, message = "!!~~!!", groups = {Modify.class, IStep3.class})
	private String memHobby;           /* 회원취미 */
	private int memMileage;            /* 회원마일리지 */
	private String memDelYn;           /* 회원삭제여부 */
	
	
	//codeVO
	private String memJobNm;                
	private String memHobbyNm;              
	
	//join
	@NotBlank(message = "체크해라", groups = IStep1.class)
	private String agreeYn;				/*이용약관동의*/
	@NotBlank(message = "필수", groups = IStep1.class)
	private String privacyYn;				/*개인정보동의*/
	private String eventYn;				/*이벤트알람수신동의*/
	private String memPassConfirm;		/*memPass랑 같은지*/
	
	
	
	public String getAgreeYn() {
		return agreeYn;
	}



	public void setAgreeYn(String agreeYn) {
		this.agreeYn = agreeYn;
	}



	public String getPrivacyYn() {
		return privacyYn;
	}



	public void setPrivacyYn(String privacyYn) {
		this.privacyYn = privacyYn;
	}



	public String getEventYn() {
		return eventYn;
	}



	public void setEventYn(String eventYn) {
		this.eventYn = eventYn;
	}



	public String getMemPassConfirm() {
		return memPassConfirm;
	}



	public void setMemPassConfirm(String memPassConfirm) {
		this.memPassConfirm = memPassConfirm;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	
	
	public String getMemJobNm() {
		return memJobNm;
	}



	public String getMemHobbyNm() {
		return memHobbyNm;
	}



	public void setMemJobNm(String memJobNm) {
		this.memJobNm = memJobNm;
	}



	public void setMemHobbyNm(String memHobbyNm) {
		this.memHobbyNm = memHobbyNm;
	}



	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemBir() {
		return memBir;
	}

	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}

	public String getMemZip() {
		return memZip;
	}

	public void setMemZip(String memZip) {
		this.memZip = memZip;
	}

	public String getMemAdd1() {
		return memAdd1;
	}

	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}

	public String getMemAdd2() {
		return memAdd2;
	}

	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}

	public String getMemHp() {
		return memHp;
	}

	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}

	public String getMemMail() {
		return memMail;
	}

	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}

	public String getMemJob() {
		return memJob;
	}

	public void setMemJob(String memJob) {
		this.memJob = memJob;
	}

	public String getMemHobby() {
		return memHobby;
	}

	public void setMemHobby(String memHobby) {
		this.memHobby = memHobby;
	}

	public int getMemMileage() {
		return memMileage;
	}

	public void setMemMileage(int memMileage) {
		this.memMileage = memMileage;
	}

	public String getMemDelYn() {
		return memDelYn;
	}

	public void setMemDelYn(String memDelYn) {
		this.memDelYn = memDelYn;
	}
	
	
	
	
	
}
