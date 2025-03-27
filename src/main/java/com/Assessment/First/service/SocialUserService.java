package com.Assessment.First.service;

import com.Assessment.First.dto.SocialUserDto;
import com.Assessment.First.entity.SocialUser;
import com.Assessment.First.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SocialUserService{

     SocialUserDto registerSocialUser(SocialUserDto socialUserDto);
     SocialUserDto getUserDetailsById(long userId) throws ResourceNotFoundException;
     SocialUserDto updateSocialUser(long userId, SocialUserDto socialUserDto) throws ResourceNotFoundException;
     Page<SocialUser> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, Boolean sortOrder);

    SocialUserDto getUserDetailsByEmail(String email) throws ResourceNotFoundException;
}
