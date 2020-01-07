package com.allen.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

/**
 * 角色信息
 *
 * @author Allen
 * @date 2020年1月7日
 * @since 1.0.0
 *
 */
@Alias("RoleDO")
public class RoleDO implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -2349442726256316953L;

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
	private LocalDateTime creatTime;

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

	public LocalDateTime getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(LocalDateTime creatTime) {
		this.creatTime = creatTime;
	}

	@Override
	public String toString() {
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("RoleDO[").append("id=").append(id).append(", roleName=").append(roleName)
				.append(", roleNote=").append(roleNote).append(", creatTime=")
				.append(creatTime).append("]");
		return sbuilder.toString();
	}
}
