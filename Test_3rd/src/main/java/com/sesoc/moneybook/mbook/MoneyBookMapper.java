package com.sesoc.moneybook.mbook;

import java.util.List;

import com.sesoc.moneybook.vo.MoneybookVO;

public interface MoneyBookMapper {

	public int insertData(MoneybookVO vo);

	public List<MoneybookVO> selectData(MoneybookVO vo);

	public int deleteData(MoneybookVO vo);

}
