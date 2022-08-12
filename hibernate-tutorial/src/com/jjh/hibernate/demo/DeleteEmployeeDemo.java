package com.jjh.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.jjh.hibernate.demo.entity.Employee;
import com.jjh.hibernate.demo.entity.Student;

public class DeleteEmployeeDemo {
	
	public static void main(String[] args) {
		
		//session factory 만들기
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")//따로 명시해 주지 않으면 하이버네이트 디폴트 설정 파일 이름 찾아서 사용할 것.
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		//session 만들기
		Session session = factory.getCurrentSession();
		
		try {
			//오브젝트 얻어올 student id
			int employeeId = 2;
			
			//session 새로 얻어오고 트랜잭션 시작하기
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			
			
			
			Query query = session.createQuery("DELETE FROM Employee "
							  				+ "WHERE id=:employeeId");
			
			query.setParameter("employeeId", employeeId);
			query.executeUpdate();
			
			//트랜잭션 커밋하기
			//이 때 데이터베이스에서도 업데이트 된다. 
			session.getTransaction().commit();
			
		
			
		}
		finally {
			factory.close();
		}
	}

}
