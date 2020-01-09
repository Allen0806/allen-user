package com.allen.user.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Mybatis 插件示例
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class UserPlugin implements Interceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserPlugin.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		LOGGER.info("Mybatis 用户插件开始...");
		Executor executor = (Executor) invocation.getTarget();
		LOGGER.info("执行器名称：" + executor.getClass().getSimpleName());
		String methodName = invocation.getMethod().getName();
		LOGGER.info("执行方法名称：" + methodName);
		Object returnObject = invocation.proceed();
		LOGGER.info("Mybatis 用户插件结束...");
		return returnObject;
	}

}
