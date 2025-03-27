package com.Assessment.First.service.impl;




import com.Assessment.First.entity.FollowerRequest;
import com.Assessment.First.entity.RequestStatus;
import com.Assessment.First.entity.SocialUser;
import com.Assessment.First.repository.FollowerRequestRepository;
import com.Assessment.First.repository.SocialUserRepository;
import com.Assessment.First.service.FollowerRequestService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FollowerRequestServiceImpl implements FollowerRequestService {

    @Autowired
    private FollowerRequestRepository followerRequestRepository;

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    private static final String PROCESS_DEFINITION_KEY = "sequential"; // BPMN ID

    @Override
    public String sendFollowRequest(Long requestById, Long requestToId) {
        SocialUser requestBy = socialUserRepository.findById(requestById).orElse(null);
        SocialUser requestTo = socialUserRepository.findById(requestToId).orElse(null);

        if (requestBy == null || requestTo == null) {
            return "User not found!";
        }

        // Check if a pending request already exists
        Optional<FollowerRequest> existingRequest = followerRequestRepository
                .findByRequestByAndRequestToAndStatus(requestBy, requestTo, RequestStatus.PENDING);

        if (existingRequest.isPresent()) {
            return "You have already sent a follow request!";
        }

        // Save the request in the database
        FollowerRequest followRequest = new FollowerRequest();
        followRequest.setRequestBy(requestBy);
        followRequest.setRequestTo(requestTo);
        followRequest.setStatus(RequestStatus.PENDING);
        followerRequestRepository.save(followRequest);

        // Start a Flowable Process
        Map<String, Object> variables = new HashMap<>();
        variables.put("requestById", requestById);
        variables.put("requestToId", requestToId);
        variables.put("requestId", followRequest.getId());
        variables.put("form_decision", "PENDING"); // Default decision value

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);

        return "Follow request sent. Process Instance ID: " + processInstance.getId();
    }

    @Override
    public String acceptFollowRequest(Long requestId, Long userId, String flowableProcessId) {
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(flowableProcessId)
                .active()
                .list();

        if (tasks.isEmpty()) {
            return "No active follow request task found for approval.";
        }

        Task task = tasks.get(0);

        // Complete the task and update the decision
        Map<String, Object> completeVariables = new HashMap<>();
        completeVariables.put("form_decision", "approve");

        taskService.complete(task.getId(), completeVariables);

        updateFollowerStatus(requestId, RequestStatus.ACCEPTED);

        return "Follow request accepted!";
    }

    @Override
    public String rejectFollowRequest(Long requestId, Long userId, String flowableProcessId) {
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(flowableProcessId)
                .active()
                .list();

        if (tasks.isEmpty()) {
            return "No active follow request task found for rejection.";
        }

        Task task = tasks.get(0);

        // Complete the task and update the decision
        Map<String, Object> completeVariables = new HashMap<>();
        completeVariables.put("form_decision", "reject");

        taskService.complete(task.getId(), completeVariables);

        updateFollowerStatus(requestId, RequestStatus.REJECTED);

        return "Follow request rejected!";
    }

    private void updateFollowerStatus(Long requestId, RequestStatus status) {
        Optional<FollowerRequest> requestOpt = followerRequestRepository.findById(requestId);

        if (requestOpt.isPresent()) {
            FollowerRequest request = requestOpt.get();
            request.setStatus(status);
            followerRequestRepository.save(request);

            if (status == RequestStatus.ACCEPTED) {
                SocialUser follower = request.getRequestBy();
                SocialUser following = request.getRequestTo();
                follower.getFollowing().add(following);
                following.getFollowers().add(follower);
                socialUserRepository.save(follower);
                socialUserRepository.save(following);
            }
        }
    }
}

