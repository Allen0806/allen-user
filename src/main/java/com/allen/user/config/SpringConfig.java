package com.allen.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.allen.user.aop.RoleAspect;
import com.allen.user.aop.UserAspect;
import com.allen.user.interceptor.UserInterceptor;

/**
 * Spring配置
 * 
 * @author Allen
 * @date 2020年1月14日
 * @since 1.0.0
 *
 */
@Configuration
public class SpringConfig implements WebMvcConfigurer {

	/**
	 * 用户切面
	 * 
	 * @return
	 */
	@Bean(name = "userAspect")
	public UserAspect userAspect() {
		return new UserAspect();
	}

	/**
	 * 角色切面
	 * 
	 * @return
	 */
	@Bean
	public RoleAspect roleAspect() {
		return new RoleAspect();
	}

	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration registration = registry.addInterceptor(new UserInterceptor());
		registration.addPathPatterns("/user/*");
	}
}
