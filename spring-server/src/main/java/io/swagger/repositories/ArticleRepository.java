package io.swagger.repositories;

import io.swagger.entities.ArticleEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;


public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
  List<ArticleEntity> findAllByOrderByLastUpdateAt();
}
