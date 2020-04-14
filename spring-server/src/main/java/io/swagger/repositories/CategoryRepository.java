package io.swagger.repositories;

import io.swagger.entities.ArticleEntity;
import io.swagger.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

}
