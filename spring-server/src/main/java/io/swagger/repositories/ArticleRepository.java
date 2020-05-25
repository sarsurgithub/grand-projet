package io.swagger.repositories;

import io.swagger.entities.ArticleEntity;
import io.swagger.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;


public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
  List<ArticleEntity> findAllByOrderByLastUpdateAt();
  List<ArticleEntity> findAllByAuthor(UserEntity author);
}
