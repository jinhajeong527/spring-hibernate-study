package com.jjh.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
	
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
			
			//Student 삭제하기
			System.out.println("Deleting student: "+ myStudent);
			//session.delete(myStudent);
			
			//id = 2 인 학생 지우기
			session.createQuery("delete from Student where id=2").executeUpdate();//이렇게 할 경우 디비에서 오브젝트 얻어올 필요가 없다. 
			
			//트랜잭션 커밋하기
			//이 때 데이터베이스에서도 업데이트 된다. 
			session.getTransaction().commit();
			
		
			
		}
		finally {
			factory.close();
		}
	}

}
