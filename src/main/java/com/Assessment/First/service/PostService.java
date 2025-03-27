package com.Assessment.First.service;

import com.Assessment.First.dto.PostDto;
import com.Assessment.First.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,long userId) throws ResourceNotFoundException;

    PostDto getById(Long id);

     List<PostDto>getAllPosts();

     PostDto update(Long postId,PostDto postDto) throws ResourceNotFoundException;

     String deleteById(Long id,Long userId) throws ResourceNotFoundException;
     List<PostDto> filterByTag(String tag) throws ResourceNotFoundException;
     List<PostDto> filterByAuthor(String author) throws ResourceNotFoundException;
}
