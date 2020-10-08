package com.demo.jpaquerydemo.model;

import com.demo.jpaquerydemo.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 客户信息
 *
 * @author wj
 * @date 2020/9/30 15:00
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_profile")
public class CustomerProfile {

    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private Customer customer;

    /**
     * 用户名称
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 用户年龄
     */
    @Column(name = "age", nullable = false, columnDefinition = "int(2)")
    private int age;

    /**
     * 用户性别
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 5)
    private GenderEnum gender;
}
