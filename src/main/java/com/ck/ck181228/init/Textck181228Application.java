package com.ck.ck181228.init;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ck.ck181228.*.controller,com.ck.ck181228.*.service"})
@MapperScan(value="com.ck.ck181228.*.mapper")
@ServletComponentScan(value= {"com.ck.ck181228.init.lis","com.ck.ck181228.init.filter"})
public class Textck181228Application {

	public static void main(String[] args) {
		SpringApplication.run(Textck181228Application.class, args);
	}
}

