package com.allen.user.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户数据传输对象
 * 
 * @author Allen
 * @date 2020年1月7日
 * @since 1.0.0
 *
 */
public class UserDTO implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 6326719591551557900L;

	/**
	 * 用户ID
	 */
	private Long id;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 用户密码
	 */
	private String userPassword;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 用户年龄
	 */
	private Integer userAge;

	/**
	 * 用户性别
	 */
	private String userGender;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 最后登录时间
	 */
	private LocalDateTime lastLoginTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@Override
	public String toString() {
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("UserDTO[").append("id=").append(id).append(", userName=").append(userName)
				.append(", userPassword=").append(userPassword).append(", realName=").append(realName)
				.append(", userAge=").append(userAge).append(", userGender=").append(userGender).append(", createTime=")
				.append(createTime).append(", lastLoginTime=").append(lastLoginTime).append("]");
		return sbuilder.toString();
	}
}
