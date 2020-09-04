package com.allen.user.constant;

import com.allen.tool.string.StringUtil;

/**
 * 性别枚举类
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
public enum GenderEnum {
	MALE("M"), FEMALE("F");

	/**
	 * 存储值
	 */
	private String value;

	/**
	 * 私有构造方法
	 * 
	 * @param value
	 */
	private GenderEnum(String value) {
		this.value = value;
	}

	/**
	 * 获取枚举对应的转换值
	 * 
	 * @return 枚举转换值
	 */
	public String value() {
		return value;
	}

	/**
	 * 根据枚举转换值获取对应的枚举对象
	 * 
	 * @param value 枚举转换值
	 * @return 枚举对象
	 */
	public static GenderEnum instanceOf(String value) {
		if (StringUtil.isBlank(value)) {
			return null;
		}
		if ("M".equals(value)) {
			return MALE;
		}
		if ("F".equals(value)) {
			return FEMALE;
		}
		return null;
	}
}
