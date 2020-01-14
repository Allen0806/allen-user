package com.allen.user.dto;

import java.io.Serializable;

/**
 * 角色数据传输对象
 *
 * @author allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
public class RoleDTO implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 2947379829053290111L;

	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色备注
	 */
	private String roleNote;

	/**
	 * 创建时间
	 */
	private String createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleNote() {
		return roleNote;
	}

	public void setRoleNote(String roleNote) {
		this.roleNote = roleNote;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("RoleDTO[").append("id=").append(id).append(", roleName=").append(roleName)
				.append(", roleNote=").append(roleNote).append(", createTime=").append(createTime).append("]");
		return sbuilder.toString();
	}
}
