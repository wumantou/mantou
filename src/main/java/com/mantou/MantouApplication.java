package com.mantou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class MantouApplication {

	public static void main(String[] args) {
		SpringApplication.run(MantouApplication.class, args);
	}

//	@Bean
//	public ServletRegistrationBean restServlet(){
//		//注解扫描上下文
////		AnnotationConfigWebApplicationContext applicationContext
////				= new AnnotationConfigWebApplicationContext();
////		//base package
////		applicationContext.scan("com.mantou");
////		//通过构造函数指定dispatcherServlet的上下文
////		DispatcherServlet rest_dispatcherServlet
////				= new DispatcherServlet(applicationContext);
//		DispatcherServlet rest_dispatcherServlet = new DispatcherServlet();
//		//用ServletRegistrationBean包装servlet
//		ServletRegistrationBean registrationBean
//				= new ServletRegistrationBean(rest_dispatcherServlet);
//		registrationBean.setLoadOnStartup(1);
//		//指定urlmapping
//		registrationBean.addUrlMappings("/");
//		//指定name，如果不指定默认为dispatcherServlet
//		registrationBean.setName("dispatcherServlet");
//		return registrationBean;
//	}
}
