package com.Assessment.First.dto;

import com.Assessment.First.entity.SocialUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private long profileId;
    private long followers;
    private long following;
    private long noOfPosts;
    private String bio;
    private SocialUser user;



}
