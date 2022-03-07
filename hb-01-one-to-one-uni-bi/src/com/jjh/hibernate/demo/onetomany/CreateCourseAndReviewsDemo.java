package com.jjh.hibernate.demo.onetomany;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Course;
import com.jjh.hibernate.demo.entity.Instructor;
import com.jjh.hibernate.demo.entity.InstructorDetail;
import com.jjh.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {
	
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
			int instructorId = 3;
			Instructor instructor = session.get(Instructor.class, instructorId);
			
			//course 만들기
			Course tempCourse = new Course("Trainer Min's Sudden Urge To Teach JAVA");
			instructor.add(tempCourse);
			
			//reviews 추가하기
			tempCourse.addReview(new Review("You can even teach java?"));
			tempCourse.addReview(new Review("I thought JAVA is some type of workout..but it's not!?!?"));
			tempCourse.addReview(new Review("Great course I loved it! see you at the Zumba class LOL"));
			
			//course save 하기.. and leverage the cascade all :-)
			session.save(tempCourse);
			
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
