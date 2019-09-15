package com.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@MapperScan("com.goods.mapper")
public class GoodsOrderViewApplication extends SpringBootServletInitializer implements WebApplicationInitializer {
	private static Logger logger = LoggerFactory.getLogger(GoodsOrderViewApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(GoodsOrderViewApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GoodsOrderViewApplication.class);
	}

}
