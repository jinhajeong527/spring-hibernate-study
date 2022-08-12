package com.jjh.hibernate.demo.manytomany;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Course;
import com.jjh.hibernate.demo.entity.Instructor;
import com.jjh.hibernate.demo.entity.InstructorDetail;
import com.jjh.hibernate.demo.entity.Review;
import com.jjh.hibernate.demo.entity.Student;

public class AddCoursesToOneStudentDemo {
	
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
			
			// 학생 아이디를 통해 Student 오브젝트 불러오기
			int studentId = 1;
			Student theStudent = session.get(Student.class, studentId);
			List<Course> theCourses = theStudent.getCourses();
			HashMap<Integer, String> courseMap = new HashMap<>();
			for(int i=0; i<theCourses.size(); i++) {
				courseMap.put(theCourses.get(i).getId(), theCourses.get(i).getTitle());
			}
			
			// 학생 아이디 1번은 현재 등록되어 있는 코스 중에서 자신이 등록하지 않는 코스라면 전부 다 등록하고자 한다.
			List<Course> courses = session.createQuery("select c from " + Course.class.getName()+ " c").getResultList();
			for(int i =0; i<courses.size(); i++) {
				if(!courseMap.containsKey(courses.get(i).getId())) { //듣고 있지 않는 수업이라면
					courses.get(i).addStudent(theStudent); //1번 학생 추가한다.
				}
			}
			
			//student save 하기.. remove 제외하고 cascading 가능하다.
			session.save(theStudent);
			
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
