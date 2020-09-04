package com.allen.user.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

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
	@NotNull(message = "用户名称不能为空")
	private String userName;

	/**
	 * 用户密码
	 */
	@NotNull(message = "用户密码不能为空")
	private String userPassword;

	/**
	 * 真实姓名
	 */
	@NotNull(message = "用户姓名不能为空")
	private String realName;

	/**
	 * 用户年龄
	 */
	@Min(value = 1, message = "年龄最小值为1")
	@Max(value = 100, message = "年龄最大值为150")
	@NotNull(message = "用户年龄不能为空")
	@Range(min = 2, max = 90, message = "用户年龄在2~90之间")
	private Integer userAge;

	/**
	 * 用户性别
	 */
	@NotNull(message = "用户性别不能为空")
	@Size(min = 1, max = 1, message = "字符串长度为1")
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
