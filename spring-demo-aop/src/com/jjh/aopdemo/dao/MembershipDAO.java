package com.jjh.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
	
	public void addAccount() {
		
		System.out.println(getClass() + ": Doing My Work: Adding A MEMBERSHIP ACCOUNT");
		
	}
	
	public void goToSleep() {
		System.out.println(getClass() + ": I'm going to sleep now....");
	}
}
