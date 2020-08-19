package com.allen.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.allen.user.model.AuAuthorityDO;
import com.allen.user.model.AuRoleAuthorityDO;

/**
 * 权限管理DAO
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
@Mapper
public interface AuthorityDAO {

	/**
	 * 保存权限信息
	 * 
	 * @param authority 权限信息
	 * @return 保存的行数
	 */
	int save(AuAuthorityDO authority);

	/**
	 * 更新权限信息
	 * 
	 * @param authority 权限信息
	 * @return 更新的行数
	 */
	int update(AuAuthorityDO authority);

	/**
	 * 根据权限主键ID删除权限信息
	 * 
	 * @param id 权限主键ID
	 * @return 删除的行数
	 */
	int delete(Long id);

	/**
	 * 保存角色权限信息
	 * 
	 * @param roleAuthoritis 角色权限列表
	 * @return 保存的行数
	 */
	int saveRoleAuthority(List<AuRoleAuthorityDO> roleAuthoritis);

	/**
	 * 删除角色权限信息，权限主键ID与角色主键ID二者必给其一，如果都给，则为And关系
	 * 
	 * @param roleId      权限主键ID
	 * @param authorityId 角色主键ID
	 * @return 删除的行数
	 */
	int deleteRoleAuthority(@Param("roleId") Long roleId, @Param("authorityId") Long authorityId);

	/**
	 * 根据权限主键ID获取权限信息
	 * 
	 * @param id 权限主键ID
	 * @return 权限信息
	 */
	AuAuthorityDO get(Long id);

	/**
	 * 根据权限名称获取权限信息
	 * 
	 * @param authorityName 权限名称
	 * @return 权限信息
	 */
	AuAuthorityDO getByAuthorityName(String authorityName);

	/**
	 * 获取权限列表，角色主键ID及权限主键ID二者必给其一，两者都给定时为And关系
	 * 
	 * @param roleId      角色主键ID
	 * @param authorityId 权限主键ID
	 * @return 权限列表
	 */
	List<AuRoleAuthorityDO> listRoleAuthority(@Param("roleId") Long roleId, @Param("authorityId") Long authorityId);
}
