package com.jjh.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.jjh.aopdemo.Account;

@Component
public class AccountDAO {
	
	public void addAccount(Account theAccount, boolean vipFlag) {
		
		System.out.println(getClass() + ": Doing My DB Work: Adding An Account");
		
	}
	
	public boolean doWork() {
		System.out.println(getClass() + ": doWork()");
		return false;
	}
}
