package com.allen.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.allen.user.model.AuUserDO;
import com.allen.user.model.UserRoleDO;

/**
 * 用户管理DAO
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
@Mapper
public interface UserDAO {

	/**
	 * 保存用户
	 * 
	 * @param user 用户信息
	 * @return 保存记录数
	 */
	int save(AuUserDO user);

	/**
	 * 更新用户
	 * 
	 * @param user 用户信息
	 * @return 更新记录数
	 */
	int update(AuUserDO user);

	/**
	 * 根据用户主键ID删除用户
	 * 
	 * @param id 用户主键ID
	 * @return 删除记录数
	 */
	int delete(Long id);

	/**
	 * 根据主键获取用户信息
	 * 
	 * @param id 用户主键ID
	 * @return 用户信息
	 */
	AuUserDO get(Long id);

	/**
	 * 根据用户名称获取用户信息
	 * 
	 * @param userName 用户名称
	 * @return 用户信息
	 */
	AuUserDO getByUserName(String userName);

	/**
	 * 根据用户名称获取用户信息，包括用户的角色信息
	 * 
	 * @param userName 用户名称
	 * @return 用户信息
	 */
	UserRoleDO getUserWithRole(String userName);
}
