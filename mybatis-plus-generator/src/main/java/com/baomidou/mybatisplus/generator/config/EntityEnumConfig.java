package com.baomidou.mybatisplus.generator.config;

import com.baomidou.mybatisplus.generator.config.po.EnumItemInfo;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.rules.ComColumnType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实体枚举类型处理
 * @author pettyfox
 * @date 2019-12-22
 */
public class EntityEnumConfig {
    public final static String REMARKS_PATTERN = ".*\\s*\\[\\s*(\\w+\\s*\\(\\s*[\\u4e00-\\u9fa5_\\-a-zA-Z0-9]+\\s*\\)\\s*:\\s*[\\u4e00-\\u9fa5_\\-a-zA-Z0-9]+\\s*\\,?\\s*)+\\s*\\]\\s*.*";
    public final static String NEED_PATTERN = "\\[\\s*((\\w+\\s*\\(\\s*[\\u4e00-\\u9fa5_\\-a-zA-Z0-9]+\\s*\\)\\s*:\\s*[\\u4e00-\\u9fa5_\\-a-zA-Z0-9]+\\s*\\,?\\s*)+)\\s*\\]";
    public final static String ITEM_PATTERN = "(\\w+)\\s*\\(\\s*([\\u4e00-\\u9fa5_\\-a-zA-Z0-9]+)\\s*\\)\\s*:\\s*([\\u4e00-\\u9fa5_\\-a-zA-Z0-9]+)";

    /**
     * 通过注释判断是否为枚举类型
     * @param comment
     * @return
     */
    public static boolean isEnumByComment(String comment){
        if(StringUtils.isBlank(comment))
            return false;
        return Pattern.matches(REMARKS_PATTERN,comment);
    }
    public static void  generatorEnumType(StrategyConfig strategyConfig,TableField tableField){
        tableField.setType(upperCaseFirst(tableField.getName()));
        String type = tableField.getType();
        tableField.setColumnType(new ComColumnType(type,strategyConfig.getSuperEnumClass()));
        List<EnumItemInfo> list = parseRemarks(tableField.getComment());
        tableField.setEnumField(list);
    }
    /**
     * 解析注释
     * @param remarks
     */
    public static List<EnumItemInfo> parseRemarks(String remarks)  {
        List<EnumItemInfo> list = new ArrayList<>();
        // 提取信息
        Pattern pattern = Pattern.compile(NEED_PATTERN);
        Matcher matcher = pattern.matcher(remarks);
        if (matcher.find() && matcher.groupCount() == 2) {
            String enumInfoStr = matcher.group(1);
            // 根据逗号切分
            String[] enumInfoStrs = enumInfoStr.split(",");

            // 提取每个节点信息
            for (String enumInfoItemStr : enumInfoStrs) {
                pattern = Pattern.compile(ITEM_PATTERN);
                matcher = pattern.matcher(enumInfoItemStr.trim());
                if (matcher.find() && matcher.groupCount() == 3) {
                    list.add(EnumItemInfo.builder()
                        .name(matcher.group(1).toUpperCase())
                        .remarks(matcher.group(3))
                        .value(matcher.group(2))
                        .build());
                }
            }
        }
        return list;
    }
    public static String upperCaseFirst(String str) {
        if ((str == null) || (str.length() == 0)) return str;
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
