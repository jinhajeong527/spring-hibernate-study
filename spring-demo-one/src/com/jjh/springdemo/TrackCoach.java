package com.jjh.springdemo;

public class TrackCoach implements Coach {
	
	
	private FortuneService fortuneService;
	
	public TrackCoach() {
	}
	
	public TrackCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		return "Just Do It: "+ fortuneService.getFortune();
	}
	
	// init 메서드 추가
	public void doMyStartupStuff() {
		System.out.println("TrackCoach: doMyStartupStuff() 메서드 안");
	}
	// destroy 메서드 추가 
	public void doMyCleanupStuff() {//no method name convention
		System.out.println("TrackCoach: doMyCleanupStuff() 메서드 안");
	}

}
