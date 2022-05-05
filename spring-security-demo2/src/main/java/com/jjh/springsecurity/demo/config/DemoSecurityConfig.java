package com.jjh.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//유저의 인메모리 인증(authentication) 추가하기
		
		UserBuilder users = User.withDefaultPasswordEncoder();
	
		auth.inMemoryAuthentication()
			.withUser(users.username("jinha").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("sooyeon").password("test123").roles("EMPLOYEE","MANAGER"))
			.withUser(users.username("yerin").password("test123").roles("EMPLOYEE","ADMIN"));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {//application, login, logout의 webpaths의 security 설정
		http.authorizeRequests()
			.antMatchers("/").hasRole("EMPLOYEE")//EMPLOYEE ROLE 있으면 홈페이지는 액세스 가능하다.
			.antMatchers("/leaders/**").hasRole("MANAGER")//EMPLOYEE ROLE 있으면 홈페이지는 액세스 가능하다.
			.antMatchers("/systems/**").hasRole("ADMIN")//EMPLOYEE ROLE 있으면 홈페이지는 액세스 가능하다.
			// Local css 파일로 로그인 css 지정하고 싶을 경우 : DemoAppConfig도 수정이 되어야 함
		 	//.antMatchers("/css/**").permitAll()
			// role에 따른 acceess를 위해 밑의 코드 주석 처리 함
			//.anyRequest().authenticated() //해당 앱으로의 어떤 리퀘스트든 authenticated 되어야 함
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage") //커스텀 로그인 페이지 보여주는 리퀘스트 맵핑
				.loginProcessingUrl("/authenticateTheUser") //로그인 폼의 POST 데이터 프로세싱할 URL
				.permitAll()// 로그인 할 필요 없이 모든 사람이 로그인 페이지에 접근할 수 있음.
			.and()
			.logout().permitAll()//로그아웃 서포트 추가
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}
	

}
