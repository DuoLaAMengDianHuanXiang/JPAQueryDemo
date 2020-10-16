package com.demo.jpaquerydemo.service.Impl;

import com.demo.jpaquerydemo.enums.GenderEnum;
import com.demo.jpaquerydemo.enums.TagNameEnum;
import com.demo.jpaquerydemo.model.Customer;
import com.demo.jpaquerydemo.model.CustomerProfile;
import com.demo.jpaquerydemo.model.Favorites;
import com.demo.jpaquerydemo.model.Tag;
import com.demo.jpaquerydemo.repository.CustomerProfileRepository;
import com.demo.jpaquerydemo.repository.CustomerRepository;
import com.demo.jpaquerydemo.service.CustomerProfileService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @author wj
 * @date 2020/9/30 16:46
 */
@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {

    @Autowired
    private CustomerProfileRepository customerProfileRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Page<CustomerProfile> listByNameAndGenderAndCustomerAccountNo(String name, GenderEnum gender, String accountNo, Pageable pageable) {
        return customerProfileRepository.findAll((Specification<CustomerProfile>) (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicateList = Lists.newArrayList();
            if (StringUtils.isNotEmpty(name)) {
                predicateList.add(criteriaBuilder.equal(root.get("name"), name));
            }
            if (gender != null) {
                predicateList.add(criteriaBuilder.equal(root.get("gender"), gender));
            }
            if (StringUtils.isNotEmpty(accountNo)) {
                Join<CustomerProfile, Customer> customerJoin = root.join("customer", JoinType.LEFT);
                predicateList.add(criteriaBuilder.equal(customerJoin.get("accountNo"), accountNo));

            }
            return criteriaQuery.where(predicateList.toArray(new Predicate[0])).getRestriction();
        }, pageable);
    }

    @Override
    public List<CustomerProfile> listByTagNameAndLessThenAgeAndFavoritesName(TagNameEnum name, Integer age, String favoritesName) {
        return customerProfileRepository.findAll((Specification<CustomerProfile>) (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicateList = Lists.newArrayList();
            boolean nameNotNull = name != null;
            boolean favoritesNameNotNull = StringUtils.isNotEmpty(favoritesName);
            if (nameNotNull || favoritesNameNotNull) {
                Join<CustomerProfile, Customer> customerJoin = root.join("customer", JoinType.LEFT);
                if (nameNotNull) {
                    Join<Customer, Tag> tagJoin = customerJoin.join("tags", JoinType.LEFT);
                    predicateList.add(criteriaBuilder.equal(tagJoin.get("name"), name));
                }
                if (favoritesNameNotNull) {
                    Join<Customer, Favorites> favoritesJoin = customerJoin.join("favorites", JoinType.LEFT);
                    predicateList.add(criteriaBuilder.equal(favoritesJoin.get("name"), favoritesName));
                }
            }
            if (age != null) {
                predicateList.add(criteriaBuilder.lessThan(root.get("age"), age));
            }
            return criteriaQuery.where(predicateList.toArray(new Predicate[0])).getRestriction();
        });
    }

    @Override
    public Page<CustomerProfile> listByTagNameAndProfileName(TagNameEnum name, String profileName, Pageable pageable) {
        return customerProfileRepository.listByTagNameAndProfileName(name, profileName, pageable);
    }

    @Override
    public void save(String name, int age, GenderEnum gender, String customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        CustomerProfile customerProfile = CustomerProfile.builder()
                .age(age)
                .gender(gender)
                .name(name)
                .customer(customer)
                .build();
        customerProfileRepository.save(customerProfile);
    }
}
