package com.jjh.springdemo;

public class GreatFortuneService implements FortuneService {
	String[] fortuneMsgs = {"you will be happy today", "something great coming for you", "you will get what you have been wanted"};
	@Override
	public String getFortune() {
		int num = (int)((Math.random()*3));
		return fortuneMsgs[num];
	}

}
