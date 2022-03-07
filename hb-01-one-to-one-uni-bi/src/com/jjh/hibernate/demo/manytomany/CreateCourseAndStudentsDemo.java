package com.jjh.hibernate.demo.manytomany;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Course;
import com.jjh.hibernate.demo.entity.Instructor;
import com.jjh.hibernate.demo.entity.InstructorDetail;
import com.jjh.hibernate.demo.entity.Review;
import com.jjh.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {
	
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
			// 학생 등록시킬 코스 불러오기
			int courseId = 10;
			Course theCourse = session.get(Course.class, courseId);
			
			// 학생 생성하기
			Student student1 = new Student("Jinha","Jeong","jhJeong@luv2code.com");
			Student student2 = new Student("Yerin","Lee","yrLee@luv2code.com");
			Student student3 = new Student("Sooyeon","Yoon","syYoon@luv2code.com");
			
			//course에 학생 추가하기
			theCourse.AddStudent(student1);
			theCourse.AddStudent(student2);
			theCourse.AddStudent(student3);
			
			//student save 하기.. remove 제외하고 cascading 가능하다.
			session.save(student1);
			session.save(student2);
			session.save(student3);

			
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
