package com.allen.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.allen.user.aop.RoleAspect;
import com.allen.user.aop.UserAspect;

/**
 * Spring配置
 * 
 * @author Allen
 * @date 2020年1月14日
 * @since 1.0.0
 *
 */
@Configuration
public class UserSpringConfig {

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
}
