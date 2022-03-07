package com.jjh.hibernate.demo.onetomany;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Course;
import com.jjh.hibernate.demo.entity.Instructor;
import com.jjh.hibernate.demo.entity.InstructorDetail;
import com.jjh.hibernate.demo.entity.Review;

public class DeleteCourseAndReviewsDemo {
	
	public static void main(String[] args) throws ParseException {
		
		//session factory 만들기
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")//따로 명시해 주지 않으면 하이버네이트 디폴트 설정 파일 이름 찾아서 사용할 것.
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		//session 만들기
		Session session = factory.getCurrentSession();
		
		try {
			
			// 트랜잭션 시작
			session.beginTransaction();
			
			//get the course
			int courseId = 14;
			Course tempCourse = session.get(Course.class, courseId);
			
			System.out.println("강사 정보:  "+tempCourse.getInstructor());
			System.out.println("강의 정보:  "+tempCourse);
			System.out.println("강의 리뷰:  "+tempCourse.getReviews());//lazy loading
			
			//course 지우기
			session.delete(tempCourse);//will do the cascade delete on the related reviews..
			
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
