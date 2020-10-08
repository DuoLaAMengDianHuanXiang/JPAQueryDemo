package com.demo.jpaquerydemo.repository;

import com.demo.jpaquerydemo.model.Customer;
import com.demo.jpaquerydemo.model.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author wj
 * @date 2020/9/30 16:48
 */
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {

    Optional<Customer> findByAccountNo(String accountNo);

    @Query(nativeQuery = true,
            value = "SELECT " +
                    "c.id, DATE_FORMAT( CONCAT( DATE(c.register_date),' ',HOUR(c.register_date), ':', FLOOR( MINUTE(c.register_date)/ 15) *15), '%H:%i') AS time " +
                    "FROM customer c " +
                    "WHERE c.register_date BETWEEN ?1 AND ?2 " +
                    "GROUP BY c.id, time")
    List<CustomerDTO> listByRegisterDateBetween(Date registerDateStart, Date registerDateEnd);
}
