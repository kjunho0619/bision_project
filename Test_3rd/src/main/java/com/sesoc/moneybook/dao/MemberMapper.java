package com.sesoc.moneybook.dao;

import com.sesoc.moneybook.vo.MemberVO;

public interface MemberMapper {

	public MemberVO login(MemberVO vo);

	public int signup(MemberVO vo);

}
