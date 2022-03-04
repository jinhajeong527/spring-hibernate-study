package com.jjh.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jjh.hibernate.demo.entity.Instructor;
import com.jjh.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {
	
	public static void main(String[] args) throws ParseException {
		
		//session factory 만들기
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")//따로 명시해 주지 않으면 하이버네이트 디폴트 설정 파일 이름 찾아서 사용할 것.
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		//session 만들기
		Session session = factory.getCurrentSession();
		
		try {
			// 트랜잭션 시작
			session.beginTransaction();
			
			//InstructorDetail object 얻어오기
			int theid = 3;
			
			
			InstructorDetail tempInstructorDetail 
				= session.get(InstructorDetail.class, theid);
			
			//InstructorDetail 출력하기
			System.out.println("InstructorDetail: "+ tempInstructorDetail);
			
			//Instructor 출력하기
			System.out.println("Instructor: "+ tempInstructorDetail.getInstructor());
			
			// 지금 현재 InstructorDetail에서 Instructor Cascade type remove 오퍼레이션은 없는 상태
			// ****둘 사이의 양방향 링크를 끊어주어야 한다.
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			//InstructorDetail 지우기
			session.delete(tempInstructorDetail);
			
		
			//트랜잭션 커밋하기
			session.getTransaction().commit();
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//connection leak 이슈 핸들링하기
			session.close();
			
			factory.close();
		}
	}

}
