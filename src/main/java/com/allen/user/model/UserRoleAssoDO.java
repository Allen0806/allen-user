package com.allen.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

/**
 * 用户角色关联信息，用于测试Mybatis的association特性
 * 
 * @author Allen
 * @date 2020年1月10日
 * @since 1.0.0
 *
 */
@Alias("UserRoleAssoDO")
public class UserRoleAssoDO implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1642801586136074827L;

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
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 关联的角色
	 */
	private AuRoleDO role;

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

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public AuRoleDO getRole() {
		return role;
	}

	public void setRole(AuRoleDO role) {
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("UserRoleAssoDO[").append("id=").append(id).append(", userId=").append(userId)
				.append(", roleId=").append(roleId).append(", createTime=").append(createTime).append(", role=")
				.append(role).append("]");
		return sbuilder.toString();
	}
}
