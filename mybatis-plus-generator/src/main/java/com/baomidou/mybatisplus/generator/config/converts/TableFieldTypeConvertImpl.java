package com.baomidou.mybatisplus.generator.config.converts;

import com.baomidou.mybatisplus.generator.config.EntityEnumConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TableFieldTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

/**
 * @author pettyfox
 * @date 2019-12-22
 */
public class TableFieldTypeConvertImpl implements TableFieldTypeConvert {
    @Override
    public void processTableFieldTypeConvert(StrategyConfig strategyConfig,TableInfo tableInfo) {
        tableInfo.getFields().forEach(tableField->{
            if(EntityEnumConfig.isEnumByComment(tableField.getComment())){
                 EntityEnumConfig.generatorEnumType(strategyConfig,tableField);
            }
        });
    }
}
