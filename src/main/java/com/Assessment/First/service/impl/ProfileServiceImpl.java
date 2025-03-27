package com.Assessment.First.service.impl;

import com.Assessment.First.dto.ProfileDto;
import com.Assessment.First.entity.Profile;
import com.Assessment.First.exception.ResourceNotFoundException;

import com.Assessment.First.repository.ProfileRepository;
import com.Assessment.First.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProfileDto getProfileById(long id) throws ResourceNotFoundException
    {
        Profile profile =profileRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("profile id not found"));

        return modelMapper.map(profile,ProfileDto.class);

    }

    @Override
    public ProfileDto updateProfileById(long id, ProfileDto profileDto) throws ResourceNotFoundException {
        Profile profile=profileRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("profile id not found"));

        profile.setBio(profileDto.getBio());
        Profile profile1=profileRepository.save(profile);


        return modelMapper.map(profile1,ProfileDto.class);



    }

    @Override
    public ProfileDto followAnotherId(long id) throws ResourceNotFoundException {
        Profile profile=profileRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("profile id not found"));
        profile.setFollowing(profile.getFollowing()+1);
        return modelMapper.map(profileRepository.save(profile),ProfileDto.class);
    }

    @Override
    public ProfileDto unfollowAnotherId(long id) throws ResourceNotFoundException {
        Profile profile=profileRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("profile id not found"));
        profile.setFollowing(profile.getFollowing()-1);
        return modelMapper.map(profileRepository.save(profile),ProfileDto.class);

    }
}
