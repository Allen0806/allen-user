package com.allen.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.allen.*")
@MapperScan("com.allen.user.*.dao.*")
public class AllenUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllenUserApplication.class, args);
	}

}
