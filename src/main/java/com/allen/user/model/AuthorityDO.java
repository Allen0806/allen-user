package com.allen.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限
 * 
 * @author Allen
 * @date 2020年1月7日
 * @since
 *
 */
public class AuthorityDO implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 7466955390339352247L;
	
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

}
