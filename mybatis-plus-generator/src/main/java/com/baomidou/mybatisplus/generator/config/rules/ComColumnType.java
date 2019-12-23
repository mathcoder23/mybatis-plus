package com.baomidou.mybatisplus.generator.config.rules;

import lombok.AllArgsConstructor;

/**
 * @author pettyfox
 * @date 2019-12-22
 */
@AllArgsConstructor
public class ComColumnType implements IColumnType {
    private String type;
    private String pkg;
    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getPkg() {
        return pkg;
    }

}
