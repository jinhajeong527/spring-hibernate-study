package com.jjh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		
		//spring config 파일 읽는다.
		//Spring한테 scan 하라고 지시하게 된다.
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//스프링 컨테이너에서 bean 얻어온다.
		Coach theCoach = context.getBean("tennisCoach", Coach.class);
		
		// bean의 메서드 호출한다
		System.out.println(theCoach.getDailyWorkout());
		
		//새롭게 추가한 운세 알려주는 메서드 호출
		System.out.println(theCoach.getDailyFortune());
		
		// context 닫는다.
		context.close();
	}

}
