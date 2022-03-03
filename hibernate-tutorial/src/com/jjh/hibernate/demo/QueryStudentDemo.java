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
			for(Student student: students) {
				System.out.println(student);
			}
			
			//트랜잭션 커밋하기
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally {
			factory.close();
		}
	}

}
