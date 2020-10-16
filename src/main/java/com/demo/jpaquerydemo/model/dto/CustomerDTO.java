package com.demo.jpaquerydemo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wj
 * @date 2020/10/8 16:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String id;

    private String registerDate;

    private Long count;

    public CustomerDTO(String id, Long count){
        this.id = id;
        this.count = count;
    }
}
