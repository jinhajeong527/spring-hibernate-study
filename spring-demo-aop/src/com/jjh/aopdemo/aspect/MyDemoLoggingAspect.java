package com.jjh.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //Aspect임을 알려준다
@Component //컴포넌트 스캐닝 대상으로 포함시키기
public class MyDemoLoggingAspect {
	
	//this is where we add all of our related advices for logging
	
	//let's start with an @Before advice
	
	//@Before("execution(public void com.jjh.aopdemo.dao.AccountDAO.addAccount())") //Pointcut expressionZ
	@Before("execution(* add*())") //어떤 return 타입이든 매치, add로 시작하는 어떤 메서드든 매치
	public void beforeAddAccountAdvice() {
		System.out.println("\n====>>> Executing @Before advice on addAccount()");
	}

}
