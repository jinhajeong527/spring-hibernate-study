package com.jjh.springdemo;

public class PilatesCoach implements Coach{
	
	FortuneService fortuneService;
	
	public PilatesCoach() {
	}
	
	//스프링 setter 의존성 주입
	public void setFortuneService(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "do stretch before the session begins";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
	
	public void startTheLesson() {
		System.out.println("Let's start the lesson of Today!");
	}
	
	public void finishTheLesson() {
		System.out.println("Done for the day!");
	}
	

}
