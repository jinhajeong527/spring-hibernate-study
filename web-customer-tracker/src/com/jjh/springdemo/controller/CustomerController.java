package com.jjh.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jjh.springdemo.dao.CustomerDAO;
import com.jjh.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// DAO 의존성 주입
	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		
		// 고객 리스트 DAO에서 받아오기
		List<Customer> theCustomers = customerDAO.getCustomers();
		
		// 고객 list model에 추가하기
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}

}
