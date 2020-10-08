package com.demo.jpaquerydemo.repository;

import com.demo.jpaquerydemo.enums.GenderEnum;
import com.demo.jpaquerydemo.enums.TagNameEnum;
import com.demo.jpaquerydemo.model.CustomerProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wj
 * @date 2020/9/30 16:49
 */
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, String>, JpaSpecificationExecutor<CustomerProfile> {

    @Query("SELECT cp FROM CustomerProfile cp " +
            "LEFT JOIN cp.customer c " +
            "LEFT JOIN c.tags t " +
            "WHERE t.name = ?1 AND cp.name = ?2")
    Page<CustomerProfile> listByTagNameAndProfileName(TagNameEnum name, String profileName, Pageable pageable);

}
