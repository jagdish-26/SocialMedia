package com.Assessment.First.controller;

import com.Assessment.First.dto.PostDto;
import com.Assessment.First.exception.ApiResponse;
import com.Assessment.First.exception.ResourceNotFoundException;
import com.Assessment.First.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping("/create/{userId}")
    public ResponseEntity<ApiResponse<PostDto>> createPost(@RequestBody PostDto postDto, @PathVariable("userId") long userId) throws ResourceNotFoundException {
           PostDto post=service.createPost(postDto,userId);

           ApiResponse<PostDto> response =  ApiResponse.<PostDto>builder()
                   .httpStatus(HttpStatus.OK.value())
                   .timeStamp(LocalDateTime.now()).errorCode("200_OK").message("Post Created").data(post).build();


           return ResponseEntity.ok(response);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<ApiResponse<PostDto>> getById(@PathVariable("id") Long id)
    {
                  PostDto postDto=service.getById(id);

                  ApiResponse<PostDto> response=ApiResponse.<PostDto>builder().httpStatus(HttpStatus.OK.value()).timeStamp(LocalDateTime.now()).errorCode("200_OK")
                          .message("Posts By Id").data(postDto).build();
                  return ResponseEntity.ok(response);
    }

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse<List<PostDto>>> getAllPosts()
    {
          List<PostDto> allPost=service.getAllPosts();
          ApiResponse<List<PostDto>> response=ApiResponse.<List<PostDto>>builder().httpStatus(HttpStatus.OK.value())
                  .timeStamp(LocalDateTime.now()).errorCode("200_OK").message("List Of Posts").data(allPost).build();
          return ResponseEntity.ok(response);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<ApiResponse<PostDto>> update(@PathVariable("id") Long postId,@RequestBody PostDto postDto) throws ResourceNotFoundException {
        PostDto update1=service.update(postId,postDto);

        ApiResponse<PostDto>response=ApiResponse.<PostDto>builder().httpStatus(HttpStatus.OK.value()).
                timeStamp(LocalDateTime.now()).errorCode("200_OK").message("updating the post").data(update1).build();

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/posts/{id}")
    public String deleteById(@PathVariable("id") Long id,@RequestParam Long userId) throws ResourceNotFoundException
    {
        return service.deleteById(id,userId);
    }

    @GetMapping("/posts/filter/tag")
   public ResponseEntity<ApiResponse<List<PostDto>>> filterByTag(@RequestParam String tag) throws ResourceNotFoundException {
        List<PostDto>post=service.filterByTag(tag);
        ApiResponse<List<PostDto>>response=ApiResponse.<List<PostDto>>builder().
                httpStatus(HttpStatus.OK.value())
                .timeStamp(LocalDateTime.now()).errorCode("200_Ok").message("List of Posts").data(post).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts/filter/author")
    public ResponseEntity<ApiResponse<List<PostDto>>> filterByAuthor(@RequestParam String author) throws ResourceNotFoundException {
        List<PostDto>post=service.filterByAuthor(author);
        ApiResponse<List<PostDto>>response=ApiResponse.<List<PostDto>>builder().httpStatus(HttpStatus.OK.value())
                .timeStamp(LocalDateTime.now()).errorCode("200_OK").message("LIst of posts by author").data(post).build();
        return ResponseEntity.ok(response);
    }
}
