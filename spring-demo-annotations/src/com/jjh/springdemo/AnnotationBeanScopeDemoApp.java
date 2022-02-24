package com.jjh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Coach theCoach = context.getBean("tennisCoach", Coach.class);
		Coach theOtherCoach = context.getBean("tennisCoach", Coach.class);
		
		boolean result = (theCoach == theOtherCoach);
		
		System.out.println("같은 결과? "+result);
		System.out.println("theCoach loaction? "+theCoach);
		System.out.println("theOtherCoach loaction? "+theOtherCoach);
		
		context.close();
	}

}
