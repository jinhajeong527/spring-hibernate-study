package com.jjh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Employee;

public class CreateEmployeeDemo {
	
	public static void main(String[] args) {
				//session factory 만들기
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")//따로 명시해 주지 않으면 하이버네이트 디폴트 설정 파일 이름 찾아서 사용할 것.
										.addAnnotatedClass(Employee.class)
										.buildSessionFactory();
				
				//session 만들기
				Session session = factory.getCurrentSession();
				
				try {
					// db에 자바 오브젝트 저장하기 위해 Session 사용하기
					// student 오브젝트 만들기
					Employee tempEmployee = new Employee("Minsu", "Kim", "minsu.kim@vtw.co.kr");
					System.out.println("전: "+tempEmployee.getId());
					// 트랜잭션 시작
					session.beginTransaction();
					
					// student 오브젝트 저장
					session.save(tempEmployee);
					System.out.println("후: "+tempEmployee.getId());
					// 트랜잭션 커밋
					session.getTransaction().commit();
					
				}
				finally {
					factory.close();
				}
			}
		
}


