package com.jjh.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Employee;

public class ReadEmployeeDemo {
	public static void main(String[] args) {
			//session factory 만들기
			SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Employee.class)
									.buildSessionFactory();
			//Session 만들기
			Session session = factory.getCurrentSession();
			
			try {
				// 트랜잭션 시작
				session.beginTransaction();
				//id가 1인 직원 정보 찾아오기
				int theId = 1;
				Employee employee = session.get(Employee.class, theId);
				System.out.println(employee);
				
				List<Employee> employees = session.createQuery("from Employee").getResultList();
				
				for(Employee tempEmployee: employees) {
					System.out.println(tempEmployee);
				}
				
				
				session.getTransaction().commit();
				
			}
			finally {
				factory.close();
			}
		
	}

}
