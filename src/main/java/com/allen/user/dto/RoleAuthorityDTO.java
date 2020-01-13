package com.allen.user.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色权限信息
 *
 * @author allen
 * @date 2020年1月12日
 * @since 1.0.0
 *
 */
public class RoleAuthorityDTO implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 114572940506979915L;

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

	/**
	 * 权限信息
	 */
	private AuthorityDTO authority;

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

	public AuthorityDTO getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityDTO authority) {
		this.authority = authority;
	}

}
