package com.allen.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.allen.tool.result.BaseResult;
import com.allen.user.dto.UserDTO;

/**
 * 用户控制层
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
@RestController
public class UserController {
	/**
	 * 保存用户
	 * 
	 * @param user 用户信息
	 * @return 保存记录数
	 */
	public BaseResult<UserDTO> save(UserDTO user) {
		return null;
	}

	/**
	 * 更新用户
	 * 
	 * @param user 用户信息
	 * @return 更新记录数
	 */
	public BaseResult<UserDTO> update(UserDTO user) {
		return null;
	}

	/**
	 * 根据用户主键ID删除用户
	 * 
	 * @param id 用户主键ID
	 * @return 删除记录路
	 */
	public BaseResult<Integer> delete(Long id) {
		return null;
	}

	/**
	 * 根据主键获取用户信息
	 * 
	 * @param id 用户主键ID
	 * @return 用户信息
	 */
	public BaseResult<UserDTO> get(Long id) {
		return null;
	}

	/**
	 * 根据用户名称获取用户信息
	 * 
	 * @param userName 用户名称
	 * @return 用户信息
	 */
	public BaseResult<UserDTO> getByUserName(String userName) {
		return null;
	}

	/**
	 * 根据用户名称获取用户信息，包括用户的角色信息
	 * 
	 * @param userName 用户名称
	 * @return 用户信息
	 */
	public BaseResult<UserDTO> getUserWithRole(String userName) {
		return null;
	}

	/**
	 * 根据用户名称获取用户信息，包括用户的角色信息（懒加载）
	 * 
	 * @param userName 用户名称
	 * @return 用户信息
	 */
	public BaseResult<UserDTO> getUserWithRole2(String userName) {
		return null;
	}
}
