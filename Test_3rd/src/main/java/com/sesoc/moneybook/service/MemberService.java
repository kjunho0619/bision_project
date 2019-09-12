package com.sesoc.moneybook.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sesoc.moneybook.dao.MemberDAO;
import com.sesoc.moneybook.vo.MemberVO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;

	public boolean login(MemberVO vo, HttpSession session) {
		// TODO Auto-generated method stub
		MemberVO result = dao.login(vo);

		if (result != null) {
			session.setAttribute("userid", result.getUserid());
			return true;
		} else {
			return false;
		}
	}

	public boolean signup(MemberVO vo) {
		// TODO Auto-generated method stub
		int result = dao.signup(vo);
		if (result == 1) {
			return true;
		} else {
			return false;
		}

	}

}
