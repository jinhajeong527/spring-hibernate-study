package com.jjh.hibernate.demo.onetomany;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Course;
import com.jjh.hibernate.demo.entity.Instructor;
import com.jjh.hibernate.demo.entity.InstructorDetail;
import com.jjh.hibernate.demo.entity.Review;
import com.jjh.hibernate.demo.entity.Student;

public class CreateInstructorCourseDemo2 {
	
	public static void main(String[] args) throws ParseException {
		
		//session factory 만들기
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")//따로 명시해 주지 않으면 하이버네이트 디폴트 설정 파일 이름 찾아서 사용할 것.
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//session 만들기
		Session session = factory.getCurrentSession();
		
		try {
			// 트랜잭션 시작
			session.beginTransaction();
			
			//새로 Instructor 엔티티 오브젝트 생성
			Instructor tempInstructor = new Instructor("Younghan", "Kim", "younghan.kim@teach.co.kr");
			InstructorDetail tempInstructordDetail = new InstructorDetail("https://www.youtube.com/user/eyeholys/featured", "workout");
			tempInstructor.setInstructorDetail(tempInstructordDetail);
			
			//tempInstructor 세션에 저장
			//session.save(tempInstructor);
			
			//course를 만든다.
			Course course1 = new Course("Basic Http Class");
			Course course2 = new Course("Practical Use Of Spring Data JPA");
			Course course3 = new Course("Java ORM Standard - Basic");
			
			//Instructor에게 Course 추가한다.
			tempInstructor.add(course1);
			tempInstructor.add(course2);
			tempInstructor.add(course3);
			
			//Course를 저장한다.
			session.save(course1);
			session.save(course2);
			session.save(course3);
			
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
