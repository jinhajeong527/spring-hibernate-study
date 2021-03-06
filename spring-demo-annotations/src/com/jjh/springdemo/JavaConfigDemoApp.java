package com.jjh.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigDemoApp {

	public static void main(String[] args) {
		
		//Spring Java Configuration 클래스 파일 읽는다.
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
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
