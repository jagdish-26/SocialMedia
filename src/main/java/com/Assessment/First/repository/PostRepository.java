package com.Assessment.First.repository;

import com.Assessment.First.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("SELECT p FROM Post p WHERE p.tag LIKE %:tags%")
    Optional<List<Post>> findByTag(@Param("tags") String tag);

    @Query("SELECT p FROM Post p WHERE p.author LIKE %:authors% ")
    Optional<List<Post>> findByAuthor(@Param("authors") String author);

}
