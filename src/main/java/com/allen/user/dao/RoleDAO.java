package com.allen.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.allen.user.model.AuRoleDO;
import com.allen.user.model.AuUserRoleDO;
import com.allen.user.model.UserRoleAssoDO;

/**
 * 角色管理DAO
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
@Repository
public interface RoleDAO {

	/**
	 * 保存角色信息
	 * 
	 * @param role 角色信息
	 * @return 保存的行数
	 */
	int save(AuRoleDO role);

	/**
	 * 更新角色信息
	 * 
	 * @param role 角色信息
	 * @return 更新的行数
	 */
	int update(AuRoleDO role);

	/**
	 * 根据角色主键ID删除角色信息
	 * 
	 * @param id 角色主键ID
	 * @return 删除的行数
	 */
	int delete(Long id);

	/**
	 * 保存用户角色信息
	 * 
	 * @param userRoles 用户角色列表
	 * @return 保存的行数
	 */
	int saveUserRole(List<AuUserRoleDO> userRoles);

	/**
	 * 根据用户主键Id或角色Id删除用户角色信息，二者必须给其一
	 * 
	 * @param userId 用户主键ID
	 * @param roleId 角色主键ID
	 * @return 删除的行数
	 */
	int deleteUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

	/**
	 * 根据角色主键ID获取角色信息
	 * 
	 * @param id 角色主键ID
	 * @return 角色信息
	 */
	AuRoleDO get(Long id);

	/**
	 * 根据角色名称获取角色信息
	 * 
	 * @param roleName 角色名称
	 * @return 角色信息
	 */
	AuRoleDO getByRoleName(String roleName);

	/**
	 * 根据用户主键ID获取该用户拥有的角色信息
	 * 
	 * @param userId 用户主键ID
	 * @return 角色信息列表
	 */
	List<AuRoleDO> listRole(Long userId);

	/**
	 * 根据角色Id获取用户角色信息，测试Mybatis的Association特性用
	 * 
	 * @param roleId 角色ID
	 * @return 用户角色信息
	 */
	UserRoleAssoDO getUserRole(Long roleId);
}
