package com.demo.jpaquerydemo.model;

import com.demo.jpaquerydemo.enums.TagNameEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * tag
 *
 * @author wj
 * @date 2020/9/30 15:28
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag")
@EqualsAndHashCode(exclude = {"customers"})
public class Tag {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", length = 32)
    protected String id;

    /**
     * tag名称
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, length = 10)
    private TagNameEnum name;

    @ManyToMany(mappedBy = "tags")
    private List<Customer> customers;
}
