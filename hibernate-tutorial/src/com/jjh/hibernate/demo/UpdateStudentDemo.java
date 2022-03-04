package com.jjh.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
	
	public static void main(String[] args) {
		
		//session factory 만들기
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")//따로 명시해 주지 않으면 하이버네이트 디폴트 설정 파일 이름 찾아서 사용할 것.
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//session 만들기
		Session session = factory.getCurrentSession();
		
		try {
			//오브젝트 얻어올 student id
			int studentid = 1;
			
			//session 새로 얻어오고 트랜잭션 시작하기
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//primary key id 사용해서 Student 오브젝트 얻어오기
			Student myStudent = session.get(Student.class, studentid);
			
			//해당 Student object 업데이트 하기
			//여기서는 메모리에서만 업데이트가 된다.
			myStudent.setFirstName("Holt");
			
			//트랜잭션 커밋하기
			//이 때 데이터베이스에서도 업데이트 된다. 
			session.getTransaction().commit();
			
			//모든 Student 업데이트 하고 싶다면?
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//모든 학생의 Last name 업데이트 하기
			session.createQuery("update Student set email='foo@gmail.com'")
				.executeUpdate();
			
			//트랜잭션 커밋하기 
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
	}

}
