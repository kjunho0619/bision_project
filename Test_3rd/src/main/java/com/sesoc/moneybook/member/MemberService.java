package com.sesoc.moneybook.member;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sesoc.moneybook.vo.MemberVO;

@Service
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;

	public boolean login(Map<String, Object> paramMap, HttpSession session) {
		// TODO Auto-generated method stub
		MemberVO result = memberMapper.login(paramMap);

		if (result != null) {
			session.setAttribute("userid", result.getUserid());
			return true;
		} else {
			return false;
		}
	}

	public boolean signup(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		int result = memberMapper.signup(paramMap);
		if (result == 1) {
			return true;
		} else {
			return false;
		}

	}

}
