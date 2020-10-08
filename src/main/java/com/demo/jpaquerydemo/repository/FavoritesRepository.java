package com.demo.jpaquerydemo.repository;

import com.demo.jpaquerydemo.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wj
 * @date 2020/9/30 16:49
 */
public interface FavoritesRepository extends JpaRepository<Favorites, String>, JpaSpecificationExecutor<Favorites> {
}
