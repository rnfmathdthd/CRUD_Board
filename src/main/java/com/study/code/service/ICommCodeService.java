package com.study.code.service;

import java.util.List;

import com.study.code.vo.CodeVO;

public interface ICommCodeService {
	public List<CodeVO> getCodeListByParent(String parentCode);
}
