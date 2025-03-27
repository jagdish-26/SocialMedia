//package com.Assessment.First.mapper;
//
//import com.Assessment.First.dto.ProfileDto;
//import com.Assessment.First.dto.SocialUserDto;
//import com.Assessment.First.entity.Profile;
//import com.Assessment.First.entity.SocialUser;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
//
//public class SocialUserMapper {
//
//    public static SocialUserDto mapToSocialUserDto(SocialUser socialUser)
//    {
//        return new SocialUserDto(
//                socialUser.getUserId(),
//                socialUser.getName(),
//                socialUser.getEmail(),
//                socialUser.getContactNo(),
//                null,
//                socialUser.getCreateDate(),
//                socialUser.getProfile(),
//                socialUser.getPost()
//
//
//        );
//    }
//
//
//    public static SocialUser mapToSocial(SocialUserDto socialUserDto)
//    {
//        return new SocialUser(
//                socialUserDto.getUserId(),
//                socialUserDto.getName(),
//                socialUserDto.getEmail(),
//                socialUserDto.getContactNo(),
//                socialUserDto.getPassword(),
//                socialUserDto.getCreateDate(),
//                socialUserDto.getProfile(),
//                socialUserDto.getPost()
//
//        );
//    }
//
//}
