package com.jjh.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jjh.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	//define field for entitymanager
	private EntityManager entityManager; //Interface used to interact with the persistence context. 
	
	//set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {//EntityManager는 스프링 부트에 의해 자동으로 생성된다. 
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		//현재의 하이버네이트 세션 얻어온다.
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		//execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		//return the result
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//현재의 하이버네이트 세션 얻어온다.
		Session currentSession = entityManager.unwrap(Session.class);
		
		Employee employee = currentSession.get(Employee.class, theId);
		
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		//현재의 하이버네이트 세션 얻어온다.
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theEmployee); //id=0이면 save/insert else update
	}

	@Override
	public void deleteById(int theId) {
		//현재의 하이버네이트 세션 얻어온다.
		Session currentSession = entityManager.unwrap(Session.class);
		//delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
	}

}
