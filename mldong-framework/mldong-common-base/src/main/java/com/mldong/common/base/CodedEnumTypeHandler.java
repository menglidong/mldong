package com.mldong.common.base;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
/**
 * 将值转成字典处理类
 * @author mldong
 *
 * @param <E>
 */
@MappedTypes({CodedEnum.class})
public class CodedEnumTypeHandler<E extends Enum<?> & CodedEnum> extends BaseTypeHandler<E> {
    private Class<E> type;

    public CodedEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter,
			JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
            ps.setNull(i, Types.TINYINT);
        } else {
            ps.setInt(i, parameter.getValue());
        }
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		int columnValue = rs.getInt(columnName);
        return rs.wasNull() ? null : enumOf(columnValue);
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		int columnValue = rs.getInt(columnIndex);
        return rs.wasNull() ? null : enumOf(columnValue);
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		int columnValue = cs.getInt(columnIndex);
        return cs.wasNull() ? null : enumOf(columnValue);
	}
    private E enumOf(int code) {
        final Optional<E> codedEnumOpt = CodedEnum.codeOf(type, code);
        if (codedEnumOpt.isPresent()) {
            return codedEnumOpt.get();
        } else {
            throw new IllegalArgumentException("Cannot convert " + code + " to " + type.getSimpleName() + " by code value.");
        }
    }
}