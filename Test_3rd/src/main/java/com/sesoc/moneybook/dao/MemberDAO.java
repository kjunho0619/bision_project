package com.sesoc.moneybook.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sesoc.moneybook.vo.MemberVO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSession sqlsession;

	public MemberVO login(MemberVO vo) {
		// TODO Auto-generated method stub
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		return mapper.login(vo);
	}

	public int signup(MemberVO vo) {
		// TODO Auto-generated method stub
		try{
			MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
			return mapper.signup(vo);
		}catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

}
