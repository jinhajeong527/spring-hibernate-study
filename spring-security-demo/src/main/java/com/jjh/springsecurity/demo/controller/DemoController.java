package com.jjh.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	
	@GetMapping("/")
	public String showHome() {
		
		//configuration setup에 따라서 /WEB-INF/view/home.jsp를 찾을 것이다.
		return "home";
	}

}
