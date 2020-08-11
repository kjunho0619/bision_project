package com.sesoc.moneybook.mbook;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sesoc.moneybook.vo.MoneybookVO;

@Service
public class MoneyBookService {
	@Autowired
	private MoneyBookMapper moneyBookMapper;

	public boolean insertData(MoneybookVO vo, HttpSession session) {
		// TODO Auto-generated method stub
		vo.setUserid((String) session.getAttribute("userid"));
		System.out.println(vo);
		if (moneyBookMapper.insertData(vo) == 1) {
			return true;
		} else {
			return false;
		}
	}

	public List<MoneybookVO> selectData(MoneybookVO vo, HttpSession session) {
		// TODO Auto-generated method stub
		vo.setUserid((String) session.getAttribute("userid"));
		System.out.println(vo);

		return moneyBookMapper.selectData(vo);
	}

	public boolean deleteData(MoneybookVO vo, HttpSession session) {
		// TODO Auto-generated method stub
		vo.setUserid((String) session.getAttribute("userid"));
		System.out.println(vo);

		if (moneyBookMapper.deleteData(vo) == 1) {
			return true;
		} else {
			return false;
		}

	}

}
