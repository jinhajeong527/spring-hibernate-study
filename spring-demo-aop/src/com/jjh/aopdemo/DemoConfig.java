package com.jjh.aopdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration//pure java configuration
@EnableAspectJAutoProxy//spring AOP proxy support
@ComponentScan("com.jjh.aopdemo")//which package to scan
public class DemoConfig {

}
