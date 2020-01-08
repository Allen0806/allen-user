package com.allen.user.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.allen.user.constant.GenderEnum;

/**
 * 性别枚举类型处理器
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
@MappedJdbcTypes(JdbcType.CHAR)
@MappedTypes(GenderEnum.class)
public class GenderEnumTypeHandler extends BaseTypeHandler<GenderEnum> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, GenderEnum parameter, JdbcType jdbcType)
			throws SQLException {
		// 如果性别不为空，则设置为存储值
		if (parameter != null) {
			ps.setString(i, parameter.value());
		}

	}

	@Override
	public GenderEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String gender = rs.getString(columnName);
		return GenderEnum.instanceOf(gender);
	}

	@Override
	public GenderEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String gender = rs.getString(columnIndex);
		return GenderEnum.instanceOf(gender);
	}

	@Override
	public GenderEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String gender = cs.getString(columnIndex);
		return GenderEnum.instanceOf(gender);
	}

}
