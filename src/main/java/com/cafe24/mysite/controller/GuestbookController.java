package com.cafe24.mysite.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService service;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<GuestbookVo> list = service.list();
		model.addAttribute("list", list);
		return "/guestbook/list";
	}
	
	@RequestMapping("/ajax")
	public String ajax() {
		return "guestbook/index-ajax";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(GuestbookVo vo) {
		service.insert(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(Model model, @PathVariable("no") Long no) {
		model.addAttribute("no", no);
		return "/guestbook/deleteform";
	}

//	public String delete(@RequestParam("no") long no, String password) {
//		service.delete(no, password);
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(GuestbookVo vo) {
		service.delete(vo);
		return "redirect:/guestbook/list";
	}
}
