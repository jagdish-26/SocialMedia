package com.Assessment.First.dto;

import com.Assessment.First.entity.Post;
import com.Assessment.First.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialUserDto {


    private long userId;
    private String name;
    private String email;
    private String contactNo;
    private String password;
    private String bio;
    private LocalDateTime createDate;
    private Profile profile;
    private List<Post> post;
}
