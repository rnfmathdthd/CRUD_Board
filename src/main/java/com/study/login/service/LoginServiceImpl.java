package com.study.login.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.login.vo.UserVO;
import com.study.member.dao.IMemberDao;
import com.study.member.vo.MemberVO;
@Service
public class LoginServiceImpl implements ILoginService {
	
	@Inject
	IMemberDao memberDao;

	@Override
	public UserVO loginCheck(String id, String pw) throws BizNotFoundException, BizPasswordNotMatchedException {
		// Connection부분 쓰지마요. try catch도 필요없어요. singlton대신 new()하세요.
			MemberVO member = memberDao.getMember(id);
			if (member == null) {
				throw new BizNotFoundException("member없다");
			} else {
				if (pw.equals(member.getMemPass())) {
					UserVO user = new UserVO();
					user.setUserId(member.getMemId());
					user.setUserName(member.getMemName());
					user.setUserPass(member.getMemPass());
					user.setUserRole(memberDao.getMemRoleByUserId(member.getMemId()));
					memberDao.getMemRoleByUserId(id);
					return user;
				} else {
					throw new BizPasswordNotMatchedException();
				}
			}
		}
	
}
