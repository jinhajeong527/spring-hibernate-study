package com.jjh.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jjh.springdemo.entity.Customer;
import com.jjh.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		// 고객 리스트 DAO에서 받아오기
		List<Customer> theCustomers = customerService.getCustomers();
		
		// 고객 list model에 추가하기
		model.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		//폼 데이터 바인딩을 위한 모델 어트리뷰트 만들기
		Customer theCustomer = new Customer();
		model.addAttribute("customer", theCustomer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		//customer 저장하기
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}

}
