package com.jjh.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;//Standard JPA API

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jjh.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDOAOJpaImpl implements EmployeeDAO {
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDOAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;	
	}
	
	@Override
	public List<Employee> findAll() {
		//쿼리를 만든다
		Query query =
				entityManager.createQuery("from Employee");
		//execute query and get result list
		List<Employee> employees = query.getResultList();
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//get Employee
		Employee employee = 
				entityManager.find(Employee.class, theId);
		//retrun employee
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		//save or updat the employee
		Employee employee = entityManager.merge(theEmployee); // id가 0이면 insert/save, 0이 아니면 update 할 것이다
		
		//디비에서 보내준 id로 업데이트 한다.  save/insert를 위해 디비에서 만들어준 아이디 사용할 수 있다.
		theEmployee.setId(employee.getId());
	}

	@Override
	public void deleteById(int theId) {
		
		// delete object with primary key
		Query theQuery = entityManager.createQuery(
				"delete from Employee where id=:employeeId"); //employeeId는 파라미터이다. 
		
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
	}

}
