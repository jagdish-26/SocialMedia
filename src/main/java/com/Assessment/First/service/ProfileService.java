package com.Assessment.First.service;

import com.Assessment.First.dto.ProfileDto;
import com.Assessment.First.exception.ResourceNotFoundException;

public interface ProfileService {
    public ProfileDto getProfileById(long id) throws ResourceNotFoundException;

    ProfileDto updateProfileById(long id,ProfileDto profileDto) throws ResourceNotFoundException;

    ProfileDto followAnotherId(long id) throws ResourceNotFoundException;

    ProfileDto unfollowAnotherId(long id) throws ResourceNotFoundException;
}
