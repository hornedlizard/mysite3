package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {
//	@Autowired
//	private UserDao userDao;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "/user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result) {
//		userDao.insert(vo);
		if (result.hasErrors()) {
			/*List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println("Object Error: "+error);
			}*/
			return "user/join";
		}
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinsuccess() {
		return "/user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "/user/login";
	}
	
	// login을 외부의 interceptor에서
	/*@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpSession session, @ModelAttribute UserVo vo, Model model) {
		
		UserVo authUser = userService.getUser(vo);
		if (authUser == null) {
			model.addAttribute("result", "fail");
			return "user/login";
		}
		// 인증됨
		session.setAttribute("authUser", authUser);
		return "redirect:/main";
	}*/
	
	/*@RequestMapping("/logout")
	public String logtout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main";
	}*/
	
//	@Auth(role=Role.ADMIN)
	@Auth
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(/*HttpSession session*/ @AuthUser UserVo authUser, Model model) {
		/* 접근제어 */
		/* UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}*/
		model.addAttribute("name", authUser.getName());
		model.addAttribute("gender", authUser.getGender());
		
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("vo", vo);
		return "/user/modify";
	}
	
	@Auth
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(HttpSession session, String checkPw, UserVo vo, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		authUser.setPassword(checkPw);
		if (userService.getUser(authUser) == null) {
			String result = "fail";
			model.addAttribute("check", result);
			model.addAttribute("name", authUser.getName());
			model.addAttribute("gender", authUser.getGender());
			model.addAttribute("gender", authUser.getGender());
			return "/user/modify";
		}
		vo.setNo(authUser.getNo());
		vo.setEmail(authUser.getEmail());
		userService.modifyUser(vo);
		session.removeAttribute("authUser");
		session.setAttribute("authUser", vo);
		return "redirect:/main";
//		return model;
	}
	
	/*
	@ExceptionHandler( UserDaoException.class )
	public String handleUserDaoException() {
		return "error/error";
	}
	*/
}
