package com.jjh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp {

	public static void main(String[] args) {
		// 스프링 설정 파일 로드
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"beanLifeCycle-applicationContext.xml");

		// 스프링 컨테이너로부터 스프링 빈을 얻어온다.(현재 싱글톤. 같은 레퍼런스 얻어와야 한다.)
		Coach theCoach = context.getBean("myCoach", Coach.class);

		// context 닫는다.
		context.close();

	}

}
