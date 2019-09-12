package com.sesoc.moneybook.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sesoc.moneybook.vo.MemberVO;
import com.sesoc.moneybook.vo.MoneybookVO;

@Repository
public class MoneyBookDAO {
	@Autowired
	private SqlSession sqlsession;

	public int insertData(MoneybookVO vo) {
		MoneyBookMapper mapper = sqlsession.getMapper(MoneyBookMapper.class);
		return mapper.insertData(vo);
	}

	public ArrayList<MoneybookVO> selectData(MoneybookVO vo) {
		MoneyBookMapper mapper = sqlsession.getMapper(MoneyBookMapper.class);
		return mapper.selectData(vo);
	}

	public int deleteData(MoneybookVO vo) {
		MoneyBookMapper mapper = sqlsession.getMapper(MoneyBookMapper.class);
		return mapper.deleteData(vo);
	}

}
