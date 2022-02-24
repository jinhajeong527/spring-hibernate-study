package com.jjh.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//스프링이 클래스 스캔할 때 사용할 어노테이션
@Component
public class TennisCoach implements Coach {
	
	@Autowired
	@Qualifier("fileFortuneService")
	FortuneService fortuneService;
	
	//기본 생성자 설정하기(tracing 목적)
	public TennisCoach() {
		System.out.println(">> TennisCoach 기본 생성자 안 ");
	}
	
	//init 메서드
	@PostConstruct
	public void init() {
		System.out.println(">> TennisCoach init method 안");
	} 
	
	//destroy 메서드
	@PreDestroy
	public void destroy() {
		System.out.println(">> TennisCoach destroy method 안");
	}
	
	/*
	@Autowired
	public void giveMeSomeLuckyMsgToday(FortuneService theFortuneService) {
		System.out.println(">> giveMeSomeLuckyMsgToday() 메서드 안 ");
		this.fortuneService = theFortuneService;
	}
	*/
	
	/*
	@Autowired //스프링은 FortuneService의 구현클래스를 찾아서 의존성을 주입해줄것이다.
	public TennisCoach(FortuneService theFortuneService) {
		this.fortuneService = theFortuneService;//전달된 파라미터를 필드에 assign해주었다.
	}
	*/
	
	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
