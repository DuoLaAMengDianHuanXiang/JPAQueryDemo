package com.demo.jpaquerydemo.service;

import com.demo.jpaquerydemo.enums.GenderEnum;
import com.demo.jpaquerydemo.enums.TagNameEnum;
import com.demo.jpaquerydemo.model.CustomerProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wj
 * @date 2020/9/30 16:46
 */
public interface CustomerProfileService {


    Page<CustomerProfile> listByNameAndGenderAndCustomerAccountNo(String name, GenderEnum gender, String accountNo, Pageable pageable);

    List<CustomerProfile> listByTagNameAndLessThenAgeAndFavoritesName(TagNameEnum name, Integer age, String favoritesName);

    Page<CustomerProfile> listByTagNameAndProfileName(TagNameEnum name, String profileName, Pageable pageable);
}
