package com.jjh.hibernate.demo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Student;

public class ReadStudentDemo {
	
	public static void main(String[] args) throws ParseException {
		
		//session factory 만들기
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")//따로 명시해 주지 않으면 하이버네이트 디폴트 설정 파일 이름 찾아서 사용할 것.
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//session 만들기
		Session session = factory.getCurrentSession();
		
		try {
			// db에 자바 오브젝트 저장하기 위해 session 사용하기
			// student 오브젝트 만들기
			String dateOfBirth = "31/12/1998";
			Date theDateOfBirth = DateUtils.parseDate(dateOfBirth);
			Student tempStudent = new Student("Sooyeon", "Yoon", "syYoon@luv2code.com", theDateOfBirth);
			System.out.println("save() 전 "+tempStudent);
			// 트랜잭션 시작
			session.beginTransaction();
			
			// student 오브젝트 저장
			session.save(tempStudent);
			
			// 트랜잭션 커밋
			session.getTransaction().commit();
			System.out.println("Student saved id is__" +tempStudent.getId());
			
			//session 새로 얻어오고 트랜잭션 시작하기
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//id(primary key)에 기반하여 student 얻어오기
			//발견 못하면 null을 리턴한다.
			Student myStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get complete: "+myStudent);
			
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
