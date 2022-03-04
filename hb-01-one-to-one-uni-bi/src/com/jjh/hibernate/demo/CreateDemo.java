package com.jjh.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Instructor;
import com.jjh.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {
	
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
			// Instuctor, InstructorDetail 오브젝트 만들기
			Instructor tempInstructor =
					new Instructor("Eunhye", "Min", "ehMin@luv2code.com");
			
			InstructorDetail tempInstructorDetail =
					new InstructorDetail(
							"http://www.youtube.com/workoutwithmin",
							"Workout");
			
			//두 오브젝트 연관짓기
			//**여기까지는 메모리에서만 연관 돼 있다. 
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// 트랜잭션 시작
			session.beginTransaction();
			
			//instructor 저장하기
			//instructor detail 오브젝트 또한 저장할 것이다.
			// CasadeType.ALL 때문.
			session.save(tempInstructor);
		
			//트랜잭션 커밋하기
			session.getTransaction().commit();
			
			
		}
		finally {
			factory.close();
		}
	}

}
