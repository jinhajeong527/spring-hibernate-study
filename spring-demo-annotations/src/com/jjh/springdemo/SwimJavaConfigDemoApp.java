package com.jjh.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimJavaConfigDemoApp {

	public static void main(String[] args) {
		
		//Spring Java Configuration 클래스 파일 읽는다.
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
		//스프링 컨테이너에서 bean 얻어온다.
		//bean method name은 bean id 이다
		SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class); 
		
		// bean의 메서드 호출한다
		System.out.println(theCoach.getDailyWorkout());
		
		//새롭게 추가한 운세 알려주는 메서드 호출
		System.out.println(theCoach.getDailyFortune());
		
		//call our new swim coach method..has the props values injected..
		System.out.println("email: "+ theCoach.getEamil());
		System.out.println("team: "+ theCoach.getTeam());
		
		// context 닫는다.
		context.close();
	}

}
