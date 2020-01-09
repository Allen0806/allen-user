package com.allen.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

/**
 * 用户角色数据对象
 * 
 * @author allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
@Alias("AuUserRoleDO")
public class AuUserRoleDO implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -9014867543649688482L;

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

	@Override
	public String toString() {
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("AuUserRoleDO[").append("id=").append(id).append(", userId=").append(userId).append(", roleId=")
				.append(roleId).append(", createTime=").append(createTime).append("]");
		return sbuilder.toString();
	}
}
