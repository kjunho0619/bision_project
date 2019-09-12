package com.sesoc.moneybook.dao;

import java.util.ArrayList;

import com.sesoc.moneybook.vo.MoneybookVO;

public interface MoneyBookMapper {

	public int insertData(MoneybookVO vo);

	public ArrayList<MoneybookVO> selectData(MoneybookVO vo);

	public int deleteData(MoneybookVO vo);

}
