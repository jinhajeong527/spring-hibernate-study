package com.jjh.springdemo;

public class BaseballCoach implements Coach {
	//dependency를 위한 private field를 정의한다.
	private FortuneService fortuneService;
	
	//의존성 주입을 위한 생성자 정의
	//스프링은 object를 construct하고 dependency를 던져준다.
	//우리는 받아서 배정해준다.
	//이 클래스는 스프링으로부터 의존성 주입을 받을 준비를 마쳤다. 
	public BaseballCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	@Override
	public String getDailyWorkout() {
		return "Spned 30 mins on batting practice";
	}

	@Override
	public String getDailyFortune() {
		//운세 메시지를 얻기위해  fortuneService를 사용한다.
		return fortuneService.getFortune();
	}

}
