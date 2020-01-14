package com.allen.user.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.allen.user.mybatis.plugin.UserPlugin;

/**
 * mybatis配置类
 * 
 * @author Allen
 * @date 2020年1月14日
 * @since 1.0.0
 *
 */
@Configuration
public class MybatisConfig {

	@Bean
	public UserPlugin userPlugin() {
		return new UserPlugin();
	}
}
