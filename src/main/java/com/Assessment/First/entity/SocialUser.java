package com.Assessment.First.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String name;
    @Column(unique = true)
    private String email;
    private String contactNo;
    private String password;
    @CreationTimestamp
    private LocalDateTime createDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnore
    private Profile profile;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> post;

    @OneToMany(mappedBy = "requestBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FollowerRequest> sentRequests;  // Requests this user sent

    @OneToMany(mappedBy = "requestTo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FollowerRequest> receivedRequests;  // Requests this user received

    // ✅ NEW: Followers and Following Lists
    @ManyToMany
    @JoinTable(
            name = "user_following",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private List<SocialUser> following;

    @ManyToMany(mappedBy = "following")
    private List<SocialUser> followers;
}
