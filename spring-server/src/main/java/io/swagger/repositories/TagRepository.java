package io.swagger.repositories;

import io.swagger.entities.TagEntity;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<TagEntity, Long> {
}
