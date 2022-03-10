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
		currentSession.saveOrUpdate(customer);
	}
	@Override
	public Customer getCustomer(int id) {
		//커렌트 하이버네이트 세션 얻는다
		Session currentSession = sessionFactory.getCurrentSession();
		
		//primary key 사용해서 database에서 해당 Customer 정보 얻어온다.
		Customer customer = currentSession.get(Customer.class, id);
		
		return customer;
	}
	@Override
	public void deleteCustomer(int id) {
		
		//커렌트 하이버네이트 세션 얻는다
		Session currentSession = sessionFactory.getCurrentSession();
		
		//primary key 사용해서 Customer 오브젝트 지우기
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", id);
		//어떤 HQL이든 그것에 맞춰서 update 해줌. general purpose
		theQuery.executeUpdate();
	}
	@Override
	public List<Customer> searchCustomers(String searchName) {
		//커렌트 하이버네이트 세션 얻는다
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = null;
		//
        // only search by name if theSearchName is not empty
        //
        if (searchName != null && searchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :searchName or lower(lastName) like :searchName", Customer.class);
            theQuery.setParameter("searchName", "%" + searchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
		return customers;
	}

}
