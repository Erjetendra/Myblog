package com.myblogapp.myblog.repository;

import com.myblogapp.myblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
