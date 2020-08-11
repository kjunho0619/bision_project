package com.sesoc.moneybook.member;

import java.util.Map;

import com.sesoc.moneybook.vo.MemberVO;

public interface MemberMapper {

	public MemberVO login(Map<String, Object> paramMap);

	public int signup(Map<String, Object> paramMap);

}
