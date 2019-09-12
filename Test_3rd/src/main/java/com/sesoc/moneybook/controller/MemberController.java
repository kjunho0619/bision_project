package com.sesoc.moneybook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sesoc.moneybook.service.MemberService;
import com.sesoc.moneybook.vo.MemberVO;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	@RequestMapping(value = "loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		return "loginForm";
	}

	@RequestMapping(value = "signupForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String signupForm() {
		return "signupForm";
	}

	@RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(MemberVO vo, HttpSession session, RedirectAttributes rttr) {
		service.login(vo, session);
		return "redirect:/";

	}

	@RequestMapping(value = "logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";

	}

	@RequestMapping(value = "cancelback", method = { RequestMethod.GET, RequestMethod.POST })
	public String cancelback() {
		return "redirect:/";

	}

	@RequestMapping(value = "signup", method = { RequestMethod.GET, RequestMethod.POST })
	public String signup(MemberVO vo, RedirectAttributes rttr) {
		boolean result = service.signup(vo);

		if (result == true) {
			return "redirect:/loginForm";
		} else {
			return "redirect:/signupForm";
		}

	}

}
