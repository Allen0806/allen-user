package com.allen.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring boot 启动类。
 * 1）@MapperScan中路径使用双星，不能使用单星
 *
 * @author Allen
 * @date 2020年1月14日
 * @since 1.0.0
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.allen")
@MapperScan(basePackages = "com.allen.**.dao")
public class AllenUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllenUserApplication.class, args);
	}

}
