package com.jjh.aopdemo;

import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jjh.aopdemo.dao.AccountDAO;
import com.jjh.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = 
				context.getBean("accountDAO", AccountDAO.class);
		
		// get membership bean from spring container
		MembershipDAO theMembershipDAO = 
				context.getBean("membershipDAO", MembershipDAO.class);
		
		// call the business method
		// @Before("execution(* add*(com.jjh.aopdemo.Account))") 이렇게 MyDemoLoggingAspect에 설정되어 있다면, theAccountDAO만 match할 것이다.
		Account myAccount = new Account();
		//theAccountDAO.addAccount(myAccount);
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();
		
		// call the membership business method
		theMembershipDAO.addAccount();
		theMembershipDAO.goToSleep();
		
		// close the context
		context.close();
	}

}
