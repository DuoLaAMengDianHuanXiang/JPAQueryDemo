package com.demo.jpaquerydemo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.List;

/**
 * @author wj
 * @date 2020/9/30 12:26
 */
@Data
@Entity
@Builder
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"account_no"})})
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", length = 32)
    protected String id;

    /**
     * 用户账号
     */
    @Column(name = "account_no", unique = true, nullable = false, length = 20)
    private String accountNo;

    /**
     * 用户密码
     */
    @Column(name = "pwd", nullable = false, length = 32)
    private String pwd;

    /**
     * 盐
     */
    @Column(name = "salt", nullable = false, length = 32)
    private String salt;

    /**
     * token
     */
    @Column(name = "token", nullable = false, length = 32)
   private String token;

    /**
     * 注册日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "register_date", nullable = false, updatable = false)
    private Date registerDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Favorites> favorites;

    /**
     * 标签列表
     */
    @ManyToMany
    @JoinTable(name = "customer_tag",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;
}
