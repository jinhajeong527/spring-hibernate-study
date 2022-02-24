package com.jjh.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("com.jjh.springdemo")//스캔할 패키지 이름 적어준다.
public class SportConfig {
	
	//sad fortune service 정의
	@Bean
	public FortuneService sadFortuneService() { //메서드 이름이 빈 아이디
		return new SadFortuneService();
	}
	
	// swim coach와 관련 의존성 주입 정의
	@Bean
	public Coach swimCoach() {
		//bean 메서드 호출. willgive the reference to the actual bean
		//스프링이 인터셉트해서 오브젝트로의 레퍼런스 제공할 것(bean scope에 기반하여)
		return new SwimCoach(sadFortuneService());
	}

}
