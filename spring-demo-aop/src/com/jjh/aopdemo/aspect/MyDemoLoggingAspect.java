package com.jjh.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //Aspect임을 알려준다
@Component //컴포넌트 스캐닝 대상으로 포함시키기
public class MyDemoLoggingAspect {
	
	//this is where we add all of our related advices for logging
	
	//let's start with an @Before advice
	
	//@Before("execution(public void com.jjh.aopdemo.dao.AccountDAO.addAccount())") // Pointcut expression
	//@Before("execution(* add*())") // 1) 어떤 return 타입이든 매치, 2) add로 시작하는 어떤 메서드든 매치
	//@Before("execution(* add*(com.jjh.aopdemo.Account))") // Fully Qualified Class Name. actual variable 넣는 것이 아니라, actual type itself of fully qualified class name
	//@Before("execution(* add*(com.jjh.aopdemo.Account, ..))") // Account Object로 시작하여, 몇 개의 인자가 오든 매치한다.
//	@Before("execution(* add*(..))") // 0 to many. does not matter how many variables this method has or not at all....
	@Before("execution(* com.jjh.aopdemo.dao.*.*(..))") // 리턴타입 all, 패키지 이름, 어떤 클래스든, 어떤 메서드든, (..): 몇 개의 파라미터든
	//@Before("execution(* add*(Account))") // 이렇게 줄 경우 invalidAbsoluteTypeName 에러 발생.
	public void beforeAddAccountAdvice() {
		System.out.println("\n====>>> Executing @Before advice on addAccount()");
	}

}
