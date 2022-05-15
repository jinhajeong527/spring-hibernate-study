package com.jjh.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc //<mvc:annotation-driven>과 비슷한 기능을 제공한다.
@ComponentScan(basePackages="com.jjh.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {
	
	//set up variable to hold the properties
	
	@Autowired
	private Environment env; // will hold data read from properties files
	
	//set up a logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	//뷰 리졸버 빈 정의하기
	@Bean
	public ViewResolver viewResolver() {
		//View Resolver를 통해 어디를 봐야할지 알 수 있다.
		InternalResourceViewResolver viewResolber = new InternalResourceViewResolver();
		
		viewResolber.setPrefix("/WEB-INF/view/");
		viewResolber.setSuffix(".jsp");
		
		return viewResolber;
	}
	
	// security datasource를 위한 빈 정의하기
	@Bean
	public DataSource securityDataSource() {
		
		// 커넥션 풀 생성하기
		ComboPooledDataSource securityDataSource
		 							= new ComboPooledDataSource(); 
		
		// JDBC 드라이버 클래스 셋팅하기
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			//이를 통해 최소한 시스템이 어떤 문제가 있다는 것은 알 수 있다.
			throw new RuntimeException(exc);
		}
		
		// connection 프로퍼티 로깅하기: 프로퍼티 파일에서 맞는 정보 읽고 있는지 확인하기 위함이다.
		logger.info(">>>> jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info(">>>> jdbc.user=" + env.getProperty("jdbc.user"));
		
		// 데이터베이스 커넥션 props 셋팅하기
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// 커넥션 풀 props 셋팅하기 : 헬퍼 메서드 사용
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	// 헬퍼 메서드가 필요하다.
	// read environment property and convert to int
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		// propVal int로 컨버트한다.
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}
	
}
