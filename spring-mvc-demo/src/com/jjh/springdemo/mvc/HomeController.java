package com.jjh.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//MVC 컨트롤러 임을 알리는 어노테이션
public class HomeController {
	
	@RequestMapping("/")
	public String showPage() {
		return "mainMenu";
	}
}
