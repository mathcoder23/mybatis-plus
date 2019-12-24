package com.baomidou.mybatisplus.generator.configtemplate;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public  class BaseEnumTypeHandler<E extends Enum<E> & BaseEnum<E,?>> extends BaseTypeHandler<BaseEnum<E,?>> {
    /**
     * 枚举的class
     */
    private Class<BaseEnum<E,?>> type;
    /**
     * 枚举的每个子类枚
     */
    private BaseEnum<E,?>[] enums;

    /**
     * 一定要有默认的构造函数，不然抛出 not found method 异常
     */
    public BaseEnumTypeHandler() {
    }

    /**
     * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现
     *
     * @param type 配置文件中设置的转换类
     */
    public BaseEnumTypeHandler(Class<BaseEnum<E,?>> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum<E,?> parameter,
                                    JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getValue());

    }

    @Override
    public BaseEnum<E,?> getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String i = rs.getString(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    @Override
    public BaseEnum<E,?> getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String i = rs.getString(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    @Override
    public BaseEnum<E,?> getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String i = cs.getString(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    /**
     * 枚举类型转换，由于构造函数获取了枚举的子类 enums，让遍历更加高效快捷，
     * <p>
     * 我将取出来的值 全部转换成字符串 进行比较，
     *
     * @param value 数据库中存储的自定义value属性
     * @return value 对应的枚举类
     */
    private BaseEnum<E,?> locateEnumStatus(String value) {
        for (BaseEnum<E,?> e : enums) {
            String a = Objects.toString(e.getValue());
            if (a.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + value + ",请核对"
                + type.getSimpleName());
    }
}
