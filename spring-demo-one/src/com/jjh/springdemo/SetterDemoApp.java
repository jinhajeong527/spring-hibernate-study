package com.jjh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

//setter injection example
public class SetterDemoApp {

	public static void main(String[] args) {
		//load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//retrieve bean from spring container
		//bean id와 첫번째 인자 맞춰 주었다.
		CricketCoach theCoach = context.getBean("myCricketCoach", CricketCoach.class);
		
		//call method on the bean
		//나중에 다시 돌아와 적을 것
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		System.out.println(theCoach.getEmailAddress());
		System.out.println(theCoach.getTeam());
		
		//close the context
		context.close();
	}

}
