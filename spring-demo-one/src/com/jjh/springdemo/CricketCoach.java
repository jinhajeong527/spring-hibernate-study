package com.jjh.springdemo;

public class CricketCoach implements Coach {
	
	private FortuneService fortuneService;
	
	//이메일 주소, 팀 필드 추가한다.
	//데이터가 application에 inject되고나면, 값들을 assign 해주기 위해 사용한다.
	private String emailAddress;
	private String team;
	

	//인자가 없는 기본 생성자를 만든다.
	public CricketCoach() {
		System.out.println("인자 없는 CricketCoach 생성자 안");
	}
	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		System.out.println("CricketCoach setEmailAddress 메서드 안");
		this.emailAddress = emailAddress;
	}


	public String getTeam() {
		return team;
	}


	public void setTeam(String team) {
		System.out.println("CricketCoach setTeam 메서드 안");
		this.team = team;
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
