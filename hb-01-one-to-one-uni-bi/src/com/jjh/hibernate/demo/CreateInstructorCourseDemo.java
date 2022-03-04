package com.jjh.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Course;
import com.jjh.hibernate.demo.entity.Instructor;
import com.jjh.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorCourseDemo {
	
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
			
			//db에서 Instructor 얻어오기
			int theId = 3; 
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			//course를 만든다.
//			Course course1 = new Course("Extream TABATA class with Min!");
//			Course course2 = new Course("No Noise HITT in your home with Min!");
//			Course course3 = new Course("Min Got Some Moves~! Zumba Class");
			Course course4 = new Course("No meaning class just about to delete");
			
			//add courses to instructor
//			tempInstructor.add(course1);
//			tempInstructor.add(course2);
//			tempInstructor.add(course3);
			tempInstructor.add(course4);
			
			//course를 저장한다.
//			session.save(course1);
//			session.save(course2);
//			session.save(course3);
			session.save(course4);
			
			//트랜잭션 커밋하기
			session.getTransaction().commit();
			
			
		}
		finally {
			//add clean up code
			session.close();
			factory.close();
		}
	}

}
