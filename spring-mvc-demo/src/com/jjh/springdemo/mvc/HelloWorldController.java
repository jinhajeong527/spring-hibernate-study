package com.jjh.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")//parent mapping: 메서드에 있는 @RequestMapping은 서브 매핑이 된다.
public class HelloWorldController {

	// initial HTML 폼을 보여주기 위한 컨트롤러 메서드
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloWorldForm";
	}

	// HTML form을 프로세스하기 위한 컨트롤러 메서드
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}

	// Form Data 읽어와서 대문자로 바꿔주는 로직있는 메서드 추가
	@RequestMapping("/processFormWithAddedLogic")
	public String convertNameToAllCaps(HttpServletRequest request, Model model) {

		// HTML form으로부터 request parameter 읽어온다.
		String name = request.getParameter("studentName");

		// 전부 대문자로 바꾼다.
		name = name.toUpperCase();

		// 메시지를 모델에 추가한다.
		model.addAttribute("namaInCap", name);

		return "helloworld";

	}

	// Form Data 읽어와서 대문자로 바꿔주는 로직있는 메서드 추가
	@RequestMapping("/processFormUsingRequestParam")
	public String processFormUsingRequestParam(
			@RequestParam("studentName") String theName, //HTTP Server request를 읽을 것
			Model model) {
		
		// @RequestParam("studentName") String theName 으로 인해 더이상 필요 없는 코드가 된다.
		// HTML form으로부터 request parameter 읽어온다.
		//String name = request.getParameter("studentName");

		// 전부 대문자로 바꾼다.
		theName = theName.toUpperCase();
		String result = "Hello! "+ theName;
		
		// 메시지를 모델에 추가한다.
		model.addAttribute("message", result);

		return "helloworld";

	}

}
