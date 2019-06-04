package com.cafe24.jblog.config;


//@Configuration
//@EnableAspectJAutoProxy
//@ComponentScan({"com.cafe24.jblog.service", "com.cafe24.jblog.repository" })
//@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {
	public AppConfig() {
		System.out.println("AppConfig");
	}
}
