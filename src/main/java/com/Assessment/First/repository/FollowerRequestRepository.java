package com.Assessment.First.repository;


import com.Assessment.First.entity.FollowerRequest;
import com.Assessment.First.entity.RequestStatus;
import com.Assessment.First.entity.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface FollowerRequestRepository extends JpaRepository<FollowerRequest, Long> {

    // ✅ Check if a follow request exists between two users with a specific status
    Optional<FollowerRequest> findByRequestByAndRequestToAndStatus(
            SocialUser requestBy,
            SocialUser requestTo,
            RequestStatus status
    );



    // ✅ Check if ANY follow request exists between two users (regardless of status)
    Optional<FollowerRequest> findByRequestByAndRequestTo(SocialUser requestBy, SocialUser requestTo);
}

