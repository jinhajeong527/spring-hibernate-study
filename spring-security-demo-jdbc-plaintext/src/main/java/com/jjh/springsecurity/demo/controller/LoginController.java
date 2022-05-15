package com.jjh.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		//return "plain-login"; //뷰 이름
		return "manual-token-login";
	}
	
	// /access-denied를 위한 request mapping 추가하기
	@GetMapping("/access-denied")
	public String showAccessDenined() {
		return "access-denied";
	}
}
