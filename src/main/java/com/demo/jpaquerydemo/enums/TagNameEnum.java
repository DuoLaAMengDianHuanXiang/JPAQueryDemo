package com.demo.jpaquerydemo.enums;

import lombok.Getter;

/**
 * @author wj
 * @date 2020/9/30 15:37
 */
public enum TagNameEnum {
    /**
     * 游泳
     */
    SWIM("游泳"),
    /**
     * 跑步
     */
    RUN("跑步");


    @Getter
    private String desc;

    private TagNameEnum(String desc) {
        this.desc = desc;
    }
}
