package com.allen.user.service;

import com.allen.tool.result.BaseResult;
import com.allen.user.dto.UserDTO;

/**
 * 用户服务层
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
public interface UserService {

	/**
	 * 保存用户
	 * 
	 * @param user 用户信息
	 * @return 保存结果
	 */
	BaseResult<UserDTO> save(UserDTO user);

	/**
	 * 更新用户
	 * 
	 * @param user 用户信息
	 * @return 更新结果
	 */
	BaseResult<UserDTO> update(UserDTO user);
	
	/**
	 * 根据用户主键ID删除用户
	 * 
	 * @param id 用户主键ID
	 * @return 删除结果
	 */
	BaseResult<Integer> delete(Long id);

	/**
	 * 根据主键获取用户信息
	 * 
	 * @param id 用户主键ID
	 * @return 用户信息
	 */
	BaseResult<UserDTO> get(Long id);

	/**
	 * 根据用户名称获取用户信息
	 * 
	 * @param userName 用户名称
	 * @return 用户信息
	 */
	BaseResult<UserDTO> getByUserName(String userName);

	/**
	 * 根据用户名称获取用户信息，包括用户的角色信息
	 * 
	 * @param userName 用户名称
	 * @return 用户信息
	 */
	BaseResult<UserDTO> getUserWithRole(String userName);
}
