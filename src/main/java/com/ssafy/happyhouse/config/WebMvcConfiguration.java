package com.ssafy.happyhouse.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.interceptor.ConfirmInterceptor;

@Configuration
@EnableAspectJAutoProxy
public class WebMvcConfiguration implements WebMvcConfigurer{
	
	@Autowired
	private ConfirmInterceptor comfirm;
	
	private final List<String> patterns = Arrays.asList("/happyhouse/*","/admin/*","/userList");
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(comfirm).addPathPatterns(patterns);
	}
	
}
