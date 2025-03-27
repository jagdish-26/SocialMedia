//package com.Assessment.First.mapper;
//
//import com.Assessment.First.dto.PostDto;
//import com.Assessment.First.entity.Post;
//
//public class PostMapper {
//
//    public static PostDto maptoPostDto(Post post)
//    {
//        return new PostDto(
//                post.getId(),
//                post.getTitle(),
//                post.getAuthor(),
//                post.getTag(),
//                post.getContent(),
//                post.getCreateDat(),
//                post.getUser()
//
//        );
//    }
//
//    public static Post mapToPost(PostDto postDto)
//    {
//        return new Post(
//                postDto.getId(),
//                postDto.getTitle(),
//                postDto.getAuthor(),
//                postDto.getTag(),
//                postDto.getContent(),
//                postDto.getCreateDat(),
//                postDto.getUser()
//
//        );
//    }
//}
//
