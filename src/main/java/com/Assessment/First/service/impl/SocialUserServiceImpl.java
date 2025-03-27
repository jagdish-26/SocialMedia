package com.Assessment.First.service.impl;
import com.Assessment.First.dto.SocialUserDto;
import com.Assessment.First.entity.Profile;
import com.Assessment.First.entity.SocialUser;
import com.Assessment.First.exception.ResourceNotFoundException;
import com.Assessment.First.repository.ProfileRepository;
import com.Assessment.First.repository.SocialUserRepository;
import com.Assessment.First.service.SocialUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SocialUserServiceImpl implements SocialUserService {

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public SocialUserDto registerSocialUser(SocialUserDto socialUserDto)
    {
        SocialUser socialUser=modelMapper.map(socialUserDto,SocialUser.class);
        socialUser.setPassword(encoder.encode(socialUser.getPassword()));
        SocialUser savedSocialUser = socialUserRepository.save(socialUser);
        Profile profile=new Profile();
        profile.setBio(socialUserDto.getBio());
        profile.setUser(savedSocialUser);
        profileRepository.save(profile);
        return modelMapper.map(savedSocialUser,SocialUserDto.class);
    }

    @Override
    public SocialUserDto getUserDetailsById(long userId) throws ResourceNotFoundException {
        SocialUser socialUser=socialUserRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found"));
        return modelMapper.map(socialUser,SocialUserDto.class);
    }



    @Override
    public SocialUserDto updateSocialUser(long userId, SocialUserDto socialUserDto) throws ResourceNotFoundException
    {
        SocialUser user=socialUserRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found"));
        SocialUser updatedUser=modelMapper.map(socialUserDto,SocialUser.class);
        updatedUser.setUserId(userId);
        updatedUser.setCreateDate(user.getCreateDate());
        socialUserRepository.save(updatedUser);
        return modelMapper.map(updatedUser,SocialUserDto.class);
    }

    @Override
    public Page<SocialUser> getAllUsers(Integer pageNumber,Integer pageSize,String sortBy,Boolean sortOrder)
    {


        Sort sort=sortOrder?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//        Sort sort = sortOrder ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);
        ModelMapper modelMapper=new ModelMapper();
        return socialUserRepository.findAll(pageable);


    }

    @Override
    public SocialUserDto getUserDetailsByEmail(String email) throws ResourceNotFoundException {
        SocialUser socialUser = socialUserRepository.findByEmail(email);
        if (socialUser == null) {
            throw new ResourceNotFoundException("User with email " + email + " not found");
        }
        return modelMapper.map(socialUser, SocialUserDto.class);
    }
}
