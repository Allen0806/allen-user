package com.allen.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限数据传输对象
 * 
 * @author allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
public class AuthorityDTO implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -1463115502416991747L;

	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 权限名称
	 */
	private String authorityName;

	/**
	 * 权限备注
	 */
	private String authorityNote;

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

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityNote() {
		return authorityNote;
	}

	public void setAuthorityNote(String authorityNote) {
		this.authorityNote = authorityNote;
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
		sbuilder.append("AuthorityDTO[").append("id=").append(id).append(", authorityName=").append(authorityName)
				.append(", authorityNote=").append(authorityNote).append(", createTime=").append(createTime)
				.append("]");
		return sbuilder.toString();
	}

}
