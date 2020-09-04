package com.allen.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.allen.user.mybatis.plugin.UserPlugin;
import com.github.pagehelper.PageInterceptor;

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

	/**
	 * 分页查询拦截器
	 * 
	 * @return
	 */
	@Bean
	public PageInterceptor pageInterceptor() {
		return new PageInterceptor();
	}
}
