package com.tinqinacademy.commentsservice.persistence.repository;

import com.tinqinacademy.commentsservice.persistence.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<UUID, Comment> {
}
