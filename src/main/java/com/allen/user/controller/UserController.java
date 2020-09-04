package com.allen.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allen.tool.result.BaseResult;
import com.allen.tool.result.StatusCode;
import com.allen.user.dto.UserDTO;
import com.allen.user.service.UserService;

/**
 * 用户控制层
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

	/**
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	/**
	 * 用户服务
	 */
	@Autowired(required = false)
	private UserService userService;

	/**
	 * 保存用户
	 * 
	 * @param user 用户信息
	 * @return 保存记录数
	 */
	@PostMapping
	public BaseResult<UserDTO> save(@Valid @RequestBody UserDTO user, Errors errors) {
		BaseResult<UserDTO> result = null;
		List<ObjectError> errorList = errors.getAllErrors();
		errorList.stream().forEach(error -> {
			String key = null;
			String msg = null;
			// 字段错误
			if (error instanceof FieldError) {
				FieldError fe = (FieldError) error;
				key = fe.getField();
				// 获取错误验证字段名
			} else {
				// 非字段错误，获取验证对象名称
				key = error.getObjectName();
			}
			// 错误信息
			msg = error.getDefaultMessage();
			LOGGER.error("用户数据校验错误，关键字：{}，错误信息：{}", key, msg);
		});
		if (errorList != null && errorList.size() > 0) {
			result = new BaseResult<>();
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("保存用户信息失败");
			return result;
		}
		try {
			result = userService.save(user);
		} catch (Exception e) {
			LOGGER.error("保存用户信息失败，用户信息[{}]", user.toString(), e);
			result = new BaseResult<>();
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("保存用户信息失败");
		}
		return result;
	}

	/**
	 * 更新用户
	 * 
	 * @param user 用户信息
	 * @return 更新记录数
	 */
	@PutMapping
	public BaseResult<UserDTO> update(@RequestBody UserDTO user) {
		BaseResult<UserDTO> result = null;
		try {
			result = userService.update(user);
		} catch (Exception e) {
			LOGGER.error("更新用户信息失败，用户信息[{}]", user.toString(), e);
			result = new BaseResult<>();
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("更新用户信息失败");
		}
		return result;
	}

	/**
	 * 根据用户主键ID删除用户
	 * 
	 * @param id 用户主键ID
	 * @return 删除记录路
	 */
	@DeleteMapping("/{id}")
	public BaseResult<Integer> delete(@PathVariable("id") Long id) {
		BaseResult<Integer> result = null;
		try {
			result = userService.delete(id);
		} catch (Exception e) {
			LOGGER.error("根据用户ID[{}]删除用户信息失败", id, e);
			result = new BaseResult<>();
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("根据用户ID[" + id + "]删除用户信息失败");
		}
		return result;
	}

	/**
	 * 根据主键获取用户信息
	 * 
	 * @param id 用户主键ID
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	public BaseResult<UserDTO> get(@PathVariable("id") Long id) {
		BaseResult<UserDTO> result = null;
		try {
			result = userService.get(id);
		} catch (Exception e) {
			LOGGER.error("根据用户ID[{}]获取用户信息失败", id, e);
			result = new BaseResult<>();
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("根据用户ID[" + id + "]获取用户信息失败");
		}
		return result;
	}

	/**
	 * 根据用户名称获取用户信息
	 * 
	 * @param userName 用户名称
	 * @return 用户信息
	 */
	@GetMapping("/name/{userName}")
	public BaseResult<UserDTO> getByUserName(@PathVariable("userName") String userName) {
		BaseResult<UserDTO> result = null;
		try {
			result = userService.getByUserName(userName);
		} catch (Exception e) {
			LOGGER.error("根据用户姓名[{}]获取用户信息失败", userName, e);
			result = new BaseResult<>();
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("根据用户姓名[" + userName + "]获取用户信息失败");
		}
		return result;
	}

	/**
	 * 根据用户名称获取用户信息，包括用户的角色信息
	 * 
	 * @param userName 用户名称
	 * @return 用户信息
	 */
	@PostMapping("/role/{userName}")
	public BaseResult<UserDTO> getUserWithRole(@PathVariable("userName") String userName) {
		BaseResult<UserDTO> result = null;
		try {
			result = userService.getUserWithRole(userName);
		} catch (Exception e) {
			LOGGER.error("根据用户姓名[{}]获取用户信息失败", userName, e);
			result = new BaseResult<>();
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("根据用户姓名[" + userName + "]获取用户信息失败");
		}
		return result;
	}
}
