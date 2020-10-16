package com.demo.jpaquerydemo.service.Impl;

import com.demo.jpaquerydemo.model.Customer;
import com.demo.jpaquerydemo.model.dto.CustomerDTO;
import com.demo.jpaquerydemo.repository.CustomerRepository;
import com.demo.jpaquerydemo.service.CustomerService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    @Autowired
    private EntityManager entityManager;


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

    @Override
    public List<CustomerDTO> listByRegisterDateBetween2(Date registerDateStart, Date registerDateEnd) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerDTO> criteriaQuery = criteriaBuilder.createQuery(CustomerDTO.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);

        Expression<String> timeStr = criteriaBuilder.function("date_format", String.class, root.<Date>get("registerDate"), criteriaBuilder.parameter(String.class, "formatStr"));
        criteriaQuery.multiselect(timeStr, criteriaBuilder.count(root.<String>get("id")));

        List<Predicate> predicateList = Lists.newArrayList();
        boolean queryRegisterDate = registerDateStart != null && registerDateEnd != null;
        if (queryRegisterDate) {
            predicateList.add(criteriaBuilder.between(root.get("registerDate"), registerDateStart, registerDateEnd));
        }
        criteriaQuery.where(predicateList.toArray(new Predicate[0]));
        criteriaQuery.groupBy(timeStr);
        TypedQuery<CustomerDTO> query = entityManager.createQuery(criteriaQuery);
        if (queryRegisterDate) {
            query.setParameter("formatStr", "%Y-%m-%d");
        }
        return query.getResultList();
    }
}
