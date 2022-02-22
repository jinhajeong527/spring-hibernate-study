package com.jjh.springdemo;

public class CricketCoach implements Coach {
	
	private FortuneService fortuneService;
	
	//인자가 없는 기본 생성자를 만든다.
	public CricketCoach() {
		System.out.println("인자 없는 CricketCoach 생성자 안");
	}
	//스프링이 의존성 주입시 호출하게될 setter 메서드
	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("CricketCoach setter 메서드 안");
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice fast bowling for 15mins";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
