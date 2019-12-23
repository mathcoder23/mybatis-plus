package com.baomidou.mybatisplus.generator.config.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EnumItemInfo{
    private String value;
    private String name;
    private String remarks;
}
