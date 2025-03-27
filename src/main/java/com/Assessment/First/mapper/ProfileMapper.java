//package com.Assessment.First.mapper;
//
//import com.Assessment.First.dto.PostDto;
//import com.Assessment.First.dto.ProfileDto;
//import com.Assessment.First.entity.Post;
//import com.Assessment.First.entity.Profile;
//import com.Assessment.First.entity.SocialUser;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
//
//public class ProfileMapper {
//
//    public static ProfileDto mapToProfileDto(Profile profile)
//    {
//        return new ProfileDto(
//                profile.getProfileId(),
//                profile.getFollowers(),
//                profile.getFollowing(),
//                profile.getNoOfPosts(),
//                profile.getBio(),
//                profile.getUser()
//        );
//    }
//
//
//    public static Profile mapToProfile(ProfileDto profileDto)
//    {
//        return new Profile(
//                profileDto.getProfileId(),
//                profileDto.getFollowers(),
//                profileDto.getFollowing(),
//                profileDto.getNoOfPosts(),
//                profileDto.getBio(),
//                profileDto.getUser()
//
//        );
//    }
//}
