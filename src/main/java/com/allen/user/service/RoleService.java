package com.allen.user.service;

import java.util.List;

import com.allen.tool.result.BaseResult;
import com.allen.user.dto.RoleDTO;
import com.allen.user.dto.UserRoleDTO;

/**
 * 角色服务层
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
public interface RoleService {

	/**
	 * 保存角色信息
	 * 
	 * @param role 角色信息
	 * @return 保存的行数
	 */
	BaseResult<RoleDTO> save(RoleDTO role);

	/**
	 * 更新角色信息
	 * 
	 * @param role 角色信息
	 * @return 更新的行数
	 */
	BaseResult<RoleDTO> update(RoleDTO role);

	/**
	 * 根据角色主键ID删除角色信息
	 * 
	 * @param id 角色主键ID
	 * @return 删除的行数
	 */
	BaseResult<Integer> delete(Long id);

	/**
	 * 保存用户角色信息
	 * 
	 * @param userRoles 用户角色列表
	 * @return 保存的行数
	 */
	BaseResult<Integer> saveUserRole(List<UserRoleDTO> userRoles);

	/**
	 * 根据用户主键Id或角色Id删除用户角色信息，二者必须给其一
	 * 
	 * @param userId 用户主键ID
	 * @param roleId 角色主键ID
	 * @return 删除的行数
	 */
	BaseResult<Integer> deleteUserRole(Long userId, Long roleId);

	/**
	 * 根据角色主键ID获取角色信息
	 * 
	 * @param id 角色主键ID
	 * @return 角色信息
	 */
	BaseResult<RoleDTO> get(Long id);

	/**
	 * 根据角色名称获取角色信息
	 * 
	 * @param roleName 角色名称
	 * @return 角色信息
	 */
	BaseResult<RoleDTO> getByRoleName(String roleName);

	/**
	 * 获取用户角色信息，用户主键ID与角色主键ID二者必须给其一，如果两者都给则是And关系
	 * 
	 * @param userId 用户主键ID
	 * @param roleId 角色主键ID
	 * @return 用户角色信息列表
	 */
	BaseResult<List<UserRoleDTO>> listUserRole(Long userId, Long roleId);

}
