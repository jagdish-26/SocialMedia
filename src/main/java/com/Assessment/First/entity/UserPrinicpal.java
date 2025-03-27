package com.Assessment.First.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UserPrinicpal implements UserDetails {
    private SocialUser socialUser;
    public UserPrinicpal(SocialUser socialUser)
    {
        this.socialUser=socialUser;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return socialUser.getPassword();
    }

    @Override
    public String getUsername() {
        return socialUser.getEmail();
    }
}
