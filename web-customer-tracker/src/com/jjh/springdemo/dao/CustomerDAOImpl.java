package com.jjh.springdemo.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jjh.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	//하이버네이트 세션 팩토리 의존성 주입하기
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//현재의 하이버네이트 세션 얻어온다.
		Session currentSession = sessionFactory.getCurrentSession();
		
		// 쿼리 만들기
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName", 
										   Customer.class);
		
		// 쿼리 실행 및 결과 리스트 받아오기
		List<Customer> customers = theQuery.getResultList();
		
		//결과 리턴하기
		return customers;
	}
	//하이버네이트 사용하여 Customer 정보 저장하기
	@Override
	public void saveCustomer(Customer customer) {
		//커렌트 하이버네이트 세션 얻는다
		Session currentSession = sessionFactory.getCurrentSession();
		//고객 정보 저장한다.
		currentSession.save(customer);
	}

}
