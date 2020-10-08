package com.demo.jpaquerydemo.enums;

import lombok.Getter;

/**
 * @author wj
 * @date 2020/9/30 14:57
 */
public enum GenderEnum {
    /**
     * 男人
     */
    MAN("男人"),
    /**
     * 女人
     */
    WOMAN("女人");

    @Getter
    private String desc;

    private GenderEnum(String desc) {
        this.desc = desc;
    }
}
