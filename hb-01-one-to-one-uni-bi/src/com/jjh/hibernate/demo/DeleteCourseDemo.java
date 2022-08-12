package com.jjh.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Course;
import com.jjh.hibernate.demo.entity.Instructor;
import com.jjh.hibernate.demo.entity.InstructorDetail;

public class DeleteCourseDemo {
	
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
			
			//db에서 Course 얻어오기
			int courseId = 13;
			Course theCourse = session.get(Course.class, courseId);
			//Course 지우기
			session.delete(theCourse);
			
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
