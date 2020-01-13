package com.allen.user.service;

import java.util.List;

import com.allen.tool.result.BaseResult;
import com.allen.user.data.AuRoleAuthorityDO;
import com.allen.user.dto.AuthorityDTO;

/**
 * 权限服务层
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
public interface AuthorityService {
	/**
	 * 保存权限信息
	 * 
	 * @param authority 权限信息
	 * @return 保存结果，如果成功返回更新的行数
	 */
	BaseResult<Integer> save(AuthorityDTO authority);

	/**
	 * 更新权限信息
	 * 
	 * @param authority 权限信息
	 * @return 更新结果，如果成功则返回更新的行数
	 */
	BaseResult<Integer> update(AuthorityDTO authority);

	/**
	 * 根据权限主键ID删除权限信息
	 * 
	 * @param id 权限主键ID
	 * @return 删除结果，如果成功则返回删除的行数
	 */
	BaseResult<Integer> delete(Long id);

	/**
	 * 保存角色权限信息
	 * 
	 * @param roleAuthoritis 角色权限列表
	 * @return 保存结果，如果成功返回保存的行数
	 */
	BaseResult<Integer> saveRoleAuthority(List<AuRoleAuthorityDO> roleAuthoritis);

	/**
	 * 根据权限主键ID获取权限信息
	 * 
	 * @param id 权限主键ID
	 * @return 权限信息
	 */
	BaseResult<AuthorityDTO> get(Long id);

	/**
	 * 根据权限名称获取权限信息
	 * 
	 * @param authorityName 权限名称
	 * @return 权限信息
	 */
	BaseResult<AuthorityDTO> getByAuthorityName(String authorityName);

	/**
	 * 根据角色主键ID获取权限列表
	 * 
	 * @param roleId 角色主键ID
	 * @return 权限列表
	 */
	BaseResult<List<AuthorityDTO>> listAuthority(String roleId);
}
