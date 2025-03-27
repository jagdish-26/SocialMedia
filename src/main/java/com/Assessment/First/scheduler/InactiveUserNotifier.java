package com.Assessment.First.scheduler;

import com.Assessment.First.entity.SocialUser;
import com.Assessment.First.repository.SocialUserRepository;
import com.Assessment.First.service.impl.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class InactiveUserNotifier {

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "${scheduler.cron.expression}")
    public void notifyInactiveUsers()
    {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(30);
        List<SocialUser> inactiveUsers = socialUserRepository.findInactiveUsers(cutoffDate);
        if (inactiveUsers.isEmpty())
        {
            System.out.println("No inactive users found at " + LocalDateTime.now());
        } else
        {
            notificationService.sendNotification(inactiveUsers);
        }
    }
}
