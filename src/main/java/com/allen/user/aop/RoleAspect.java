package com.allen.user.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.allen.user.dto.RoleDTO;

/**
 * 用户管理切面
 * 
 * @author Allen
 * @date 2020年1月14日
 * @since 1.0.0
 *
 */
@Aspect
public class RoleAspect implements Ordered {

	/**
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleAspect.class);

	@Pointcut("execution(* com.allen.user.service.**.save(..)) && bean('roleServiceImpl')")
	public void pointCut() {

	}

	@Before("pointCut() && args(role)")
	public void before(JoinPoint point, RoleDTO role) {
		LOGGER.info("before .........");
		Object target = point.getTarget();
		LOGGER.info("target class : {}", target.getClass().getName());
		LOGGER.info("role info : {}", role.toString());
	}

	@Around("pointCut()")
	public void around(ProceedingJoinPoint jp) throws Throwable {
		LOGGER.info("around before .........");
		jp.proceed();
		LOGGER.info("around after .........");
	}

	@After("pointCut()")
	public void after() {
		LOGGER.info("after .........");
	}

	@AfterReturning("pointCut()")
	public void afterReturning() {
		LOGGER.info("afterReturning .........");
	}

	@AfterThrowing("pointCut()")
	public void afterThrowing() {
		LOGGER.info("afterThrowing .........");
	}
	
	@Override
	public int getOrder() {
		return 2;
	}
}
