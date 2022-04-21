package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {
Post findByTitle(String title);
List<Post> findByCategories(Category category);
}
