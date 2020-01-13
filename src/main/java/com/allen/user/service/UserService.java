package com.allen.user.service;

import java.util.List;

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
	 * @return 保存记录数
	 */
	BaseResult<Integer> save(UserDTO user);

	/**
	 * 批量保存用户
	 * 
	 * @param users 用户列表
	 * @return 保存记录数
	 */
	BaseResult<Integer> saveBatch(List<UserDTO> users);

	/**
	 * 更新用户
	 * 
	 * @param user 用户信息
	 * @return 更新记录数
	 */
	BaseResult<Integer> update(UserDTO user);

	/**
	 * 根据用户主键ID删除用户
	 * 
	 * @param id 用户主键ID
	 * @return 删除记录路
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

	/**
	 * 根据用户名称获取用户信息，包括用户的角色信息（懒加载）
	 * 
	 * @param userName 用户名称
	 * @return 用户信息
	 */
	BaseResult<UserDTO> getUserWithRole2(String userName);
}
