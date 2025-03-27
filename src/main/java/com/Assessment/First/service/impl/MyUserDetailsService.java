package com.Assessment.First.service.impl;

import com.Assessment.First.entity.SocialUser;
import com.Assessment.First.entity.UserPrinicpal;
import com.Assessment.First.exception.ResourceNotFoundException;
import com.Assessment.First.repository.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SocialUserRepository socialUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

                       SocialUser socialUser =socialUserRepository.findByEmail(username);
                       if(socialUser == null)
                       {
                           throw new UsernameNotFoundException("username not found");
                       }
                       else
                       {
                           return new UserPrinicpal(socialUser);
                       }
    }
}
