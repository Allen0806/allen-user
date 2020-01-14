package com.allen.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allen.tool.result.BaseResult;
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
	public BaseResult<UserDTO> save(@RequestBody UserDTO user) {
		BaseResult<UserDTO> result = null;
		try {
			result = userService.save(user);
		} catch (Exception e) {
			LOGGER.error("保存用户信息失败，用户信息[{}]", user.toString(), e);
			result = new BaseResult<>();
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
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
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
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
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
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
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
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
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
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
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("根据用户姓名[" + userName + "]获取用户信息失败");
		}
		return result;
	}

	/**
	 * 根据用户名称获取用户信息，包括用户的角色信息（懒加载）
	 * 
	 * @param userName 用户名称
	 * @return 用户信息
	 */
	@GetMapping("/role2/{userName}")
	public BaseResult<UserDTO> getUserWithRole2(@PathVariable("userName") String userName) {
		BaseResult<UserDTO> result = null;
		try {
			result = userService.getUserWithRole2(userName);
		} catch (Exception e) {
			LOGGER.error("根据用户姓名[{}]获取用户信息失败", userName, e);
			result = new BaseResult<>();
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("根据用户姓名[" + userName + "]获取用户信息失败");
		}
		return result;
	}
}
