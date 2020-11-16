package com.allen.user.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户管理拦截器
 * 
 * @author Allen
 * @date 2020年1月15日
 * @since 1.0.0
 *
 */
public class UserInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String handlerName = handler.getClass().getName();
		logger.info("preHandle方法，处理器名称：{}", handlerName);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String handlerName = handler.getClass().getName();
		logger.info("postHandle方法，处理器名称：{}", handlerName);
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String handlerName = handler.getClass().getName();
		logger.info("afterCompletion方法，处理器名称：{}", handlerName);
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
