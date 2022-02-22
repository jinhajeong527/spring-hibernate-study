package com.jjh.springdemo;

public class MyApp {

	public static void main(String[] args) {
		//create the object
		Coach theCoach = new TrackCoach(); //FortuneService TrackCoach에 의존성 주입 생성자로 하면서, 인자가 없는 생성자가 사라지게 되었고,
		// 그래서 여기서 에러가 났었던 것.
		
		// use the object
		//theCoach는 Coach를 인터페이스로 선언했기 때문에, 제네릭 인터페이스가 된다.
		//Coach를 구현한 어떤 것과도 working with 할 수 있다.
		System.out.println(theCoach.getDailyWorkout());
	}

}
