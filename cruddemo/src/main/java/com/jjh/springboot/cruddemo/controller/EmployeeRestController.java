package com.jjh.springboot.cruddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjh.springboot.cruddemo.entity.Employee;
import com.jjh.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
		private EmployeeService employeeService;
		
		@Autowired //생성자 의존성 주입
		public EmployeeRestController(EmployeeService employeeService) {
			this.employeeService = employeeService;
		}
		// "/employees" 엔드포인트 노출시키고, list of employees return 한다.
		@GetMapping("/employees")
		public List<Employee> findAll() {
			return employeeService.findAll();
		}
		// add mapping for GET /employees/{employeeId}
		@GetMapping("/employees/{employeeId}")
		public Employee getEmployee(@PathVariable int employeeId) {
			Employee theEmployee = employeeService.findById(employeeId);
			if(theEmployee == null)
				throw new RuntimeException("Employee id not found - " + employeeId);
			return theEmployee;
		}
		
		@PostMapping("/employees") //Employee data는 JSON의 형태로 리퀘스트 바디에 올 것이다. JSON 데이터를 우리의 임플로이 오브젝트에 바인딩하기 위해서는 @RequestBody 어노테이션이 필요하다.
		public Employee addEmployee(@RequestBody Employee theEmployee) {
			theEmployee.setId(0);
			employeeService.save(theEmployee);
			return theEmployee;
			
		}
		
		@PutMapping("/employees")
		public Employee updateEmployee(@RequestBody Employee theEmployee) {
			employeeService.save(theEmployee);
			return theEmployee;
		}
		//add mapping for delete
		@DeleteMapping("/employees/{employeeId}")
		public String deleteEmployee(@PathVariable int employeeId) {
			Employee temEmployee = employeeService.findById(employeeId);
			
			//throw exception if null
			if(temEmployee == null) {
				throw new RuntimeException("Employee id not found - " +employeeId);
				
			}
			employeeService.deleteById(employeeId);
			return "Deleted employee id - "+employeeId;
		}
}
