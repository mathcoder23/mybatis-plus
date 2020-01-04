//
//
//package com.baomidou.mybatisplus.generator.configtemplate;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class JSONObjectTypeHandler extends BaseTypeHandler<JSONObject> {
//
//    public void setNonNullParameter(PreparedStatement preparedStatement, int i, JSONObject jsonObject, JdbcType jdbcType) throws SQLException {
//        preparedStatement.setString(i, JSON.toJSONString(jsonObject));
//    }
//
//    public JSONObject getNullableResult(ResultSet rs, String s) throws SQLException {
//        return JSON.parseObject(rs.getString(s));
//    }
//
//    public JSONObject getNullableResult(ResultSet rs, int i) throws SQLException {
//        return JSON.parseObject(rs.getString(i));
//    }
//
//    public JSONObject getNullableResult(CallableStatement cs, int i) throws SQLException {
//        return JSON.parseObject(cs.getString(i));
//    }
//}
