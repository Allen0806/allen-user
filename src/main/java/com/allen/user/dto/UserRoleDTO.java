package com.allen.user.dto;

import java.io.Serializable;

/**
 * 用户角色传输对象
 *
 * @author allen
 * @date 2020年1月12日
 * @since 1.0.0
 *
 */
public class UserRoleDTO implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -2625470805873371463L;

	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 用户主键ID
	 */
	private Long userId;

	/**
	 * 角色主键ID
	 */
	private Long roleId;

	/**
	 * 角色对象
	 */
	private RoleDTO role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}
	
}
