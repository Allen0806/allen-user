package com.allen.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

/**
 * 角色权限数据对象类
 * 
 * @author allen
 * @date 2020年1月7日
 * @since 1.0.0
 *
 */
@Alias("AuRoleAuthorityDO")
public class AuRoleAuthorityDO implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1788815243160618L;

	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 角色主键ID
	 */
	private Long roleId;

	/**
	 * 权限主键ID
	 */
	private Long authorityId;

	/**
	 * 是否有效
	 */
	private Boolean valid;

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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
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
		sbuilder.append("UserDO[").append("id=").append(id).append(", roleId=").append(roleId).append(", authorityId=")
				.append(authorityId).append(", valid=").append(valid).append(", createTime=").append(createTime)
				.append("]");
		return sbuilder.toString();
	}

}
