package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@SessionAttributes("authUser")
@RequestMapping("user2")
public class UserController2 {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login2";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(
			@ModelAttribute UserVo vo,
			// Model 객체를 요청해서 authUser로 넣음
			Model model) {
		
		UserVo authUser = userService.getUser(vo);
		model.addAttribute("authUser", authUser);
		
		return "redirect:/main";
	}
	
	@ResponseBody
	@RequestMapping(value="/modify")
	public String modify(
			/* session 값 받아오기 (타입에 session attribute와 같은
			 * 이름으로 하면 request가 아니라 session에서 가져옴 */
			@ModelAttribute("authUser") UserVo authUser) {
		System.out.println(authUser);
		return "UserController2:modify";
	}
	
	@RequestMapping("/logout")
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/user2/login";
	}
}
