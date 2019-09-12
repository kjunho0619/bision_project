package com.sesoc.moneybook.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sesoc.moneybook.service.MoneyBookService;
import com.sesoc.moneybook.vo.MoneybookVO;

@Controller
public class MoneyBookController {

	@Autowired
	private MoneyBookService service;

	@RequestMapping(value = "mymoneybook", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		return "moneybook";
	}

	@RequestMapping(value = "insertData", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public boolean insertData(MoneybookVO vo, HttpSession session) {
		boolean result = service.insertData(vo, session);

		return result;
	}

	@RequestMapping(value = "selectData", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ArrayList<MoneybookVO> selectData(MoneybookVO vo, HttpSession session) {
		ArrayList<MoneybookVO> list = new ArrayList<>();
		list = service.selectData(vo, session);

		return list;
	}

	@RequestMapping(value = "deleteData", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public boolean deleteData(MoneybookVO vo, HttpSession session) {
		System.out.println(vo);
		boolean result = service.deleteData(vo, session);

		return result;
	}

}
