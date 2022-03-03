package com.jjh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class TestJdbc {
	
	public static void main(String[] args) {
		
		//DB 커넥션 얻기위한 정보들 
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
		String user = "hbstudent";
		String password = "hbstudent";
		
		try {
			
			Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
			System.out.println("커넥션 성공!");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
