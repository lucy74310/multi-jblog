package com.cafe24.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.web.FileuploadConfig;
import com.cafe24.config.web.MVCConfig;
import com.cafe24.config.web.MessageConfig;
import com.cafe24.config.web.SecurityConfig;

@Configuration
// spring-servlet.xml의 <mvc: 붙은 태그들
@EnableAspectJAutoProxy
// spring-servlet.xml의 <context:component-scan base-package="com.cafe24.springex.controller" />
@ComponentScan({"com.cafe24.jblog.controller", "com.cafe24.jblog.controller.api"})
@Import({MVCConfig.class, SecurityConfig.class, MessageConfig.class, FileuploadConfig.class})
public class WebConfig {
	
	
	
}
