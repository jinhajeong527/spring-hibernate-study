package com.jjh.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GreatFortuneService implements FortuneService {
	
	String[] fortuneMsgs = {
			"You will be happy today",
			"Something great coming for you",
			"You will get what you have been wanted",
			"The journey is the reward",
			"Dilligence is the mother of good luck"
			};
	
	
	//random 숫자 생성기 
	private Random random = new Random();
	
	@Override
	public String getFortune() {
		
		//배열 크기에 맞는 랜덤 숫자 받아오기
		int index = random.nextInt(fortuneMsgs.length);
		
		return fortuneMsgs[index];
	}

}
