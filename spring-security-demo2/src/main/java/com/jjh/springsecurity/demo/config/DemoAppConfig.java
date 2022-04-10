package com.jjh.springsecurity.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc //<mvc:annotation-driven>과 비슷한 기능을 제공한다.
@ComponentScan(basePackages="com.jjh.springsecurity.demo")
public class DemoAppConfig //implements WebMvcConfigurer 
{
	
	//뷰 리졸버 빈 정의하기
	@Bean
	public ViewResolver viewResolver() {
		//View Resolver를 통해 어디를 봐야할지 알 수 있다.
		InternalResourceViewResolver viewResolber = new InternalResourceViewResolver();
		
		viewResolber.setPrefix("/WEB-INF/view/");
		viewResolber.setSuffix(".jsp");
		
		return viewResolber;
	}
	
	/* local css 파일 적용하고 싶을 경우에 설정 필요
	   Spring Security에서 권한 주지 않을 경우 블락할 것이기 때문.
	   WebMvcConfigurer implements도 필요하다.
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	}
	*/

}
