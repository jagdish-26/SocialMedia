package com.Assessment.First.service.impl;

import com.Assessment.First.entity.SocialUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class NotificationService {
    private final Random random = new Random();
    public void sendNotification(List<SocialUser> inactiveUser) {
        // Define an array of possible messages
        String[] messages = {
                "We miss your posts!",
                "Hey, it's been a while—come back and share your thoughts!",
                "Your community is waiting for you!",
                "Don't be a stranger—post something new today!"
        };
        // Pick a random message from the array
        String message = messages[random.nextInt(messages.length)];
        // Simulate sending a notification (replace this with real notification logic)

        for(SocialUser user:inactiveUser)
        {
            System.out.println(message+user.getEmail());
        }
    }
}
