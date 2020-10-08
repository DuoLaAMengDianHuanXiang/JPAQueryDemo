package com.demo.jpaquerydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author wj
 * @date 2020/9/30 12:18
 */
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.demo.jpaquerydemo.**"})
public class JPAQueryDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JPAQueryDemoApplication.class, args);
    }
}
