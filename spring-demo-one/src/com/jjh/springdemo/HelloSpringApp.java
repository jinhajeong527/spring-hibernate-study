package com.jjh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {
		
		// load the spring configuraion file
		// 실제 config파일 이름을 생성자에 적어준다.
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		// retrieve bean from spring container
		// bean id와 인터페이스 인자로 넣어준다.
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		//call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		//let's call our new method for fortunes
		System.out.println(theCoach.getDailyFortune());
		
		//close the context
		context.close();

	}

}
