package com.jjh.springdemo;

import org.springframework.stereotype.Component;

@Component//스프링이 빈으로 등록하게 해준다.
public class HappyFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		return "Something great coming for you very soon!";
	}

}
