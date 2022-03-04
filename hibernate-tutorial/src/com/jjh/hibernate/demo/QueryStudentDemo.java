package com.jjh.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Student;

public class QueryStudentDemo {
	
	public static void main(String[] args) {
		
		//session factory 만들기
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")//따로 명시해 주지 않으면 하이버네이트 디폴트 설정 파일 이름 찾아서 사용할 것.
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//session 만들기
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			//query students
			List<Student> students = session.createQuery("from Student").getResultList();
			
			//student 리스트 콘솔에 출력해보기
			displayStudents(students);
			
			//조건문 where 사용
			students = session.createQuery("from Student s where s.firstName='Yerin'").getResultList();
			displayStudents(students);
			
			//OR 사용
			students = session.createQuery("from Student s where"
								+ " s.firstName='Yerin' OR s.lastName='Kim'").getResultList();
			displayStudents(students);
			
			//LIKE 사용
			students = session.createQuery("from Student s where"
								+ " s.email LIKE '%luv2code.com'").getResultList();
			System.out.println("이메일이 luv2code.com 인 학생 리스트");
			displayStudents(students);
			
			
			
			//트랜잭션 커밋하기
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		for(Student student: students) {
			System.out.println(student);
		}
	}

}
