package io.swagger.repositories;

import io.swagger.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String name);
}