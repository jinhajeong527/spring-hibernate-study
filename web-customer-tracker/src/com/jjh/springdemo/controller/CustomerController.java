package com.jjh.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("customer", theCustomer);//customer-form.jsp form의 modelAttribute와 값 일치함.
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		//customer 저장하기
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
		
		//get the customer from the service
		Customer customer = customerService.getCustomer(id);
		//set customer as a model attribute to pre-populate the form 
		model.addAttribute("customer", customer);
		//send over to our form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId")int id) {
		//customer 삭제하기
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
	
	//고객 이름으로 검색하기
	@GetMapping("/search")
    public String searchCustomers(@RequestParam("searchName") String searchName,
                                    Model model) {
        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(searchName);
                
        // add the customers to the model
        model.addAttribute("customers", theCustomers);
        return "list-customers";        
    }
	
	
	

}
