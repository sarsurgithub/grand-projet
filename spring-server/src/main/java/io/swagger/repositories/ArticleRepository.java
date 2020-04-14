package io.swagger.repositories;

import io.swagger.entities.ArticleEntity;
import org.springframework.data.repository.CrudRepository;


public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {

}
