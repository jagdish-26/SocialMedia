package com.Assessment.First.repository;

import com.Assessment.First.entity.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface SocialUserRepository extends JpaRepository<SocialUser,Long> {
    SocialUser findByEmail(String email);


    @Query("SELECT su FROM SocialUser su " +
                "WHERE EXISTS (" +
                "   SELECT p FROM Post p WHERE p.user = su AND p.createDat < ?1"
            +")"
         )
    List<SocialUser> findInactiveUsers( LocalDateTime cutoff);
}
