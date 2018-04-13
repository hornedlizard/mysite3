package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/gallery")
@Controller
public class GalleryController {
	
	@RequestMapping("")
	public String gallery() {
		return "gallery/index";
	}
}
