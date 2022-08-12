package com.jjh.springdemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/genre")
@CrossOrigin
public class GenreController {
	
	@GetMapping("/list")
	public String listCustomers() {
		return "list-genres";
	}
	
}
