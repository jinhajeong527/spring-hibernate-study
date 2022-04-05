package com.jjh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Employee;

public class SaveAndMergeDemo {
	public static void main(String[] args) {
		//session factory 만들기
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")//따로 명시해 주지 않으면 하이버네이트 디폴트 설정 파일 이름 찾아서 사용할 것.
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		//session 만들기
		Session session = factory.getCurrentSession();
		try {
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			//새로 Employee 엔티티 오브젝트 만든다. 이 때 상태는 Transient
			Employee newEmployee = new Employee("Minji", "Lee", "minji.lee@vtw.co.kr");
			
			//case 1: merge 사용하고, 트랜잭션으로 커밋: exception 발생 안하고 디비에도 저장 됨
			session.merge(newEmployee);
			System.out.println("merge() 메서드 실행");
			session.getTransaction().commit();
			
			/*
			case 2: merge 사용하고, evict 사용하여 DETACHED 상태로 만들어 줌
			session.merge(newEmployee);
			session.evict(newEmployee);
			*/
			
			/*
			case 3: update 사용하고, evict 사용: Exception 발생하지 않지만 데이터베이스에 저장되지도 않음
			session.update(newEmployee);
			session.evict(newEmployee);
			*/
			
			/*
			case 4: update 사용하고, 트랜잭션 커밋: Exception 발생
			session.update(newEmployee);
			session.getTransaction().commit();
			*/
			
			
			
		}
		finally {
			factory.close();
		}
				
	}

}
