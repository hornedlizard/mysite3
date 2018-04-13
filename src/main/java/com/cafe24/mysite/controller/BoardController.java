package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.PageVo;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@RequestMapping("/list")
	public String list(Integer page, String kwd, Model model) {
		PageVo vo = new PageVo();
		if(page == null) {
			page = 1;
		}
		vo.setPage(page);
		int totalData = service.countData(kwd);
		vo.setTotalData(totalData);
		vo.paging();
		List<BoardVo> list = service.list(vo, kwd);
		model.addAttribute("list", list);
		model.addAttribute("page", vo);
		model.addAttribute("kwd", kwd);
		return "/board/list";
	}

	@RequestMapping("/view")
	public Model view(long no, String kwd, Model model, Integer page) {
		model.addAttribute("page", page);
		model.addAttribute("kwd", kwd);
		System.out.println(no);
		model.addAttribute("vo", service.view(no));
		service.increaseHits(no);
		return model;
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(HttpSession session) {
		UserVo user = (UserVo) session.getAttribute("authUser");
		if (user == null) {
			return "redirect:/user/login";
		}
		return "/board/write";
	}

	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session, 
				String title, String content,
				@RequestParam(value="groupNo", required=true, defaultValue="0") long groupNo,
				@RequestParam(value="orderNo", required=true, defaultValue="0") Long orderNo,
				@RequestParam(value="depth", required=true, defaultValue="-1") Long depth,
				@RequestParam(value="page", required=true, defaultValue="1") Integer page) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			return "redirect:/user/login";
		}
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUserVo(authUser);
		
		service.increaseOrderNo(groupNo, orderNo);
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo);
		vo.setDepth(depth);
		
		service.write(vo);
		
		return "redirect:/board/list?page="+page;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(HttpSession session, @RequestParam("no") long no, Integer page) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/user/login";
		}
		UserVo vo = service.view(no).getUserVo();
		if (authUser.getNo() != service.view(no).getUserVo().getNo()) {
			return "redirect:/board/list";
		}
		service.delete(no);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(HttpSession session, long no, Integer page, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/user/login";
		}
		if (authUser.getNo() != service.view(no).getUserVo().getNo()) {
			return "redirect:/user/login";
		}
		model.addAttribute("vo", service.view(no));
		model.addAttribute("page", page);
		return "/board/modify";
	}

	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(BoardVo vo, Integer page, Model model) {
		service.update(vo);
		model.addAttribute("page", page);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="reply", method=RequestMethod.GET)
	public String reply(Long no, Integer page, Model model) {
		BoardVo vo = service.view(no);
		
		model.addAttribute("groupNo", vo.getGroupNo());
		model.addAttribute("orderNo", vo.getOrderNo());
		model.addAttribute("depth", vo.getDepth());
		model.addAttribute("page", page);
		
		return "/board/write";
	}
}
