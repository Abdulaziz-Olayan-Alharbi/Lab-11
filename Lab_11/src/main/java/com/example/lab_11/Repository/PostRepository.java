package com.example.lab_11.Repository;

import com.example.lab_11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(Integer id);
    @Query("select p from Post p where p.CategoryId = ?1")
    List<Post> getByCategory(Integer categoryId);
    @Query("select p from Post p where p.publishDate between ?1 and ?2")
    List<Post> getByDateBetween(LocalDate start, LocalDate end);
    Post findPostByTitle(String title);
    List<Post> findPostsByUserId(Integer userId);
}
