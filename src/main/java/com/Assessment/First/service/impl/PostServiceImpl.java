package com.Assessment.First.service.impl;

import com.Assessment.First.dto.PostDto;
import com.Assessment.First.entity.Post;
import com.Assessment.First.entity.SocialUser;
import com.Assessment.First.exception.ResourceNotFoundException;

import com.Assessment.First.repository.PostRepository;
import com.Assessment.First.repository.SocialUserRepository;
import com.Assessment.First.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SocialUserRepository socialUserRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto,long userId) throws ResourceNotFoundException
    {
        SocialUser socialUser=socialUserRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("userId not found"));
        socialUser.getProfile().setNoOfPosts((socialUser.getProfile().getNoOfPosts()+1));
        Post post = modelMapper.map(postDto, Post.class);
        post.setUser(socialUser);
        Post savedPost=postRepository.save(post);
        return modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public PostDto getById(Long id)
    {
        Post post=postRepository.findById(id).orElseThrow();
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts() {

            List<Post> postList=postRepository.findAll();
            List<PostDto> postDtos=new ArrayList<>();
            for (Post post:postList)
            {
                postDtos.add(modelMapper.map(post,PostDto.class));
            }
            return postDtos;

//            return postList.stream().map((post)->PostMapper.maptoPostDto(post))
//                    .collect(Collectors.toList());

    }

    @Override
    public PostDto update(Long postId,PostDto postDto) throws ResourceNotFoundException {
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("not found"));
        post.setAuthor(postDto.getAuthor());
        post.setTag(postDto.getTag());
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        return modelMapper.map(postRepository.save(post),PostDto.class);
    }

    @Override
    public String deleteById(Long id,Long userId) throws ResourceNotFoundException
    {
        SocialUser socialUser=socialUserRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("userId not found"));
        socialUser.getProfile().setNoOfPosts(socialUser.getProfile().getNoOfPosts()-1);
        postRepository.deleteById(id);
        return "Post deleted successfully";
    }

    @Override
    public List<PostDto> filterByTag(String tag) throws ResourceNotFoundException {
        List<Post>post =postRepository.findByTag(tag).orElseThrow(()->new ResourceNotFoundException("Post with tag name not found"));
        return post.stream().map((posts)->modelMapper.map(posts,PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> filterByAuthor(String author) throws ResourceNotFoundException {
        List<Post>post =postRepository.findByAuthor(author).orElseThrow(()->new ResourceNotFoundException("Post with auhtor name not found"));
        return post.stream().map((posts)->modelMapper.map(posts,PostDto.class))
                .collect(Collectors.toList());
    }

}