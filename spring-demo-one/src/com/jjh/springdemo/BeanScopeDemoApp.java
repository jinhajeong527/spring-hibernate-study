package com.jjh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		// 스프링 설정 파일 로드
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");//읽어올 설정파일 바꿔주었다. 원한다면 여러개 설정파일도 콤마로 구분하여 인자로 넣어줄 수 있다.
		
		// 스프링 컨테이너로부터 스프링 빈을 얻어온다.(현재 싱글톤. 같은 레퍼런스 얻어와야 한다.)
		Coach theCoach = context.getBean("myCoach", Coach.class);
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		Coach pilatesCoach = context.getBean("pilatesCoach", Coach.class);//싱글톤
		Coach otherCoach = context.getBean("pilatesCoach", Coach.class);
		
		// 두개가 같은 빈인지 체크한다. => 메모리의 같은 area를 포인팅하고 있는지, 같은 오브젝트 가리키고 있는지 확인하기 위해서.
		boolean result = (pilatesCoach == otherCoach);
		System.out.println("같은 오브젝트 가리키고 있는가?:  "+result);
		
		//메모리 위치도 출력해본다.
		//이 오브젝트의 toString()메서드 출력 값 보여줄 것이다. (클래스 이름 + 내부 메모리 주소로 이루어져 있음)
		System.out.println("theCoach 메모리 위치: "+ pilatesCoach); 
		System.out.println("alphaCoach 메모리 위치: "+ otherCoach);
		
		//context 닫는다.
		context.close();
				
	}

}
