package com.demo.jpaquerydemo.service.Impl;

import com.demo.jpaquerydemo.model.Customer;
import com.demo.jpaquerydemo.model.dto.CustomerDTO;
import com.demo.jpaquerydemo.repository.CustomerRepository;
import com.demo.jpaquerydemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @author wj
 * @date 2020/9/30 16:47
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer findById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer findByAccountNo(String accountNo) {
        return customerRepository.findByAccountNo(accountNo).orElse(null);
    }

    @Override
    public List<CustomerDTO> listByRegisterDateBetween(Date registerDateStart, Date registerDateEnd) {
        return customerRepository.listByRegisterDateBetween(registerDateStart, registerDateEnd);
    }
}
