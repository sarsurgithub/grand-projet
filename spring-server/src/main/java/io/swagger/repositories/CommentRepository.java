package io.swagger.repositories;

import io.swagger.entities.CommentEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository  extends CrudRepository<CommentEntity, Long> {
}
