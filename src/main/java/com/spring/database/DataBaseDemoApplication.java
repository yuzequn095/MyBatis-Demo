package com.spring.database;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.spring.database.dao")
@ComponentScan("com.spring.database")
public class DataBaseDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DataBaseDemoApplication.class, args);
	}

}
