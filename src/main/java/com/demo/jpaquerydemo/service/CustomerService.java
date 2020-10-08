package com.demo.jpaquerydemo.service;

import com.demo.jpaquerydemo.model.Customer;
import com.demo.jpaquerydemo.model.dto.CustomerDTO;

import java.util.Date;
import java.util.List;

/**
 * @author wj
 * @date 2020/9/30 16:45
 */
public interface CustomerService {

    Customer findById(String id);

    Customer findByAccountNo(String accountNo);


    List<CustomerDTO> listByRegisterDateBetween(Date registerDateStart, Date registerDateEnd);

}
