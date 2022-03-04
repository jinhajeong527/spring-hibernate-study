package com.jjh.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Instructor;
import com.jjh.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {
	
	public static void main(String[] args) throws ParseException {
		
		//session factory 만들기
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")//따로 명시해 주지 않으면 하이버네이트 디폴트 설정 파일 이름 찾아서 사용할 것.
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		//session 만들기
		Session session = factory.getCurrentSession();
		
		try {
			
		
			
			// 트랜잭션 시작
			session.beginTransaction();
			//primary key로 instructor 얻어오기
			int theId = 2; 
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			//지우기
			if(tempInstructor != null) {
				//delete object 또한 지워질 것이다. CascadeType.ALL 이기 때문이다.
				session.delete(tempInstructor);
			}
			
		
			//트랜잭션 커밋하기
			session.getTransaction().commit();
			
			
		}
		finally {
			factory.close();
		}
	}

}
