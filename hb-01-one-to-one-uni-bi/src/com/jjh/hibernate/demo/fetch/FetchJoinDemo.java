package com.jjh.hibernate.demo.fetch;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.jjh.hibernate.demo.entity.Course;
import com.jjh.hibernate.demo.entity.Instructor;
import com.jjh.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {
	
	public static void main(String[] args) throws ParseException {
		
		//session factory 만들기
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")//따로 명시해 주지 않으면 하이버네이트 디폴트 설정 파일 이름 찾아서 사용할 것.
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		//session 만들기
		Session session = factory.getCurrentSession();
		
		try {
			// 트랜잭션 시작
			session.beginTransaction();
			
			// Hibernate Query with HQL
			
			//db에서 Instructor 얻어오기
			int theId = 3; 
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
										+"JOIN FETCH i.courses "
										+"where i.id=:theInstructorId",
										Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			//execute query and get instructor
			Instructor tempInstructor = query.getSingleResult(); // => instructor 및 courses 한번에 로드할 것이다.
			
			System.out.println("###Instructor: "+ tempInstructor);
			
			//트랜잭션 커밋하기
			session.getTransaction().commit();
			
			//세션닫기
			session.close();
			
			//해당 instructor의 코스 얻어오기: 세션이 닫힌 후 얻어오는 LAZY DATA
			System.out.println("###Courses of "+ tempInstructor.getFirstName()+" : "+ tempInstructor.getCourses());
			
			
		}
		finally {
			//add clean up code
			session.close();
			factory.close();
		}
	}

}
