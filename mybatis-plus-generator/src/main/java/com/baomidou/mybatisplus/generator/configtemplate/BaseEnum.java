package com.baomidou.mybatisplus.generator.configtemplate;

public interface BaseEnum<E extends Enum<E>, T> {
    /**
     * 获取枚举的值
     * @return 枚举的值
     */
    T getValue();

    /**
     * 枚举名称，在json序列号显示
     * @return
     */
    String getName();
}
