package com.sesoc.moneybook.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sesoc.moneybook.dao.MoneyBookDAO;
import com.sesoc.moneybook.vo.MoneybookVO;

@Service
public class MoneyBookService {
	@Autowired
	private MoneyBookDAO dao;

	public boolean insertData(MoneybookVO vo, HttpSession session) {
		// TODO Auto-generated method stub
		vo.setUserid((String) session.getAttribute("userid"));
		System.out.println(vo);
		if (dao.insertData(vo) == 1) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<MoneybookVO> selectData(MoneybookVO vo, HttpSession session) {
		// TODO Auto-generated method stub
		vo.setUserid((String) session.getAttribute("userid"));
		System.out.println(vo);

		return dao.selectData(vo);
	}

	public boolean deleteData(MoneybookVO vo, HttpSession session) {
		// TODO Auto-generated method stub
		vo.setUserid((String) session.getAttribute("userid"));
		System.out.println(vo);

		if (dao.deleteData(vo) == 1) {
			return true;
		} else {
			return false;
		}

	}

}
