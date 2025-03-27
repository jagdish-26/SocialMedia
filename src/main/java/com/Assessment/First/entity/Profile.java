package com.Assessment.First.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long profileId;
    private long followers;
    private long following;
    private long noOfPosts;
    private String bio;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private SocialUser user;


}



