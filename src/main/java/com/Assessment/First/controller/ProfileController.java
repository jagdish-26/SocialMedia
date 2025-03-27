package com.Assessment.First.controller;

import com.Assessment.First.dto.ProfileDto;
import com.Assessment.First.exception.ApiResponse;
import com.Assessment.First.exception.ResourceNotFoundException;
import com.Assessment.First.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/profiles")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // GETMAPPING  /profiles/{id}--get profile details based on id
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<ProfileDto>>getProfileById(@PathVariable("id")long id) throws ResourceNotFoundException
    {
          ProfileDto profile=profileService.getProfileById(id);
        ApiResponse<ProfileDto>response=ApiResponse.<ProfileDto>builder().httpStatus(HttpStatus.OK.value())
                .timeStamp(LocalDateTime.now()).errorCode("200_OK")
                .message("Profile by Id").data(profile).build();
        return ResponseEntity.ok(response);
    }

    // PUT /profiles/{id}: Update profile bio Based on id
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<ProfileDto>>updateProfileBioById(@PathVariable("id")long id,@RequestBody ProfileDto profileDto) throws ResourceNotFoundException {
                  ProfileDto profileDto1=profileService.updateProfileById(id,profileDto);
                  ApiResponse<ProfileDto>response=ApiResponse.<ProfileDto>builder()
                          .httpStatus(HttpStatus.OK.value())
                          .timeStamp(LocalDateTime.now())
                          .errorCode("200_Ok")
                          .message("Profile Updated")
                          .data(profileDto1).build();
                 return  ResponseEntity.ok(response);
    }

    //POST /profiles/{id}/follow: Follow another id
    @PostMapping("/{id}/follow")
    public ResponseEntity<ApiResponse<ProfileDto>>followAnotherId(@PathVariable("id")long id) throws ResourceNotFoundException {

             ProfileDto profile=profileService.followAnotherId(id);
             ApiResponse<ProfileDto>response=ApiResponse.<ProfileDto>builder()
                     .httpStatus(HttpStatus.OK.value())
                     .timeStamp(LocalDateTime.now()).errorCode("200_OK").message("followed").data(profile).build();
             return ResponseEntity.ok(response);
    }

    //POST /profiles/{id}/unfollow: Unfollow a id
    @PostMapping("{id}/unfollow")
    public ResponseEntity<ApiResponse<ProfileDto>>unfollowAnotherId(@PathVariable("id")long id) throws ResourceNotFoundException
    {
        ProfileDto profile=profileService.unfollowAnotherId(id);
        ApiResponse<ProfileDto>response=ApiResponse.
                <ProfileDto>builder().httpStatus(HttpStatus.OK.value())
                .timeStamp(LocalDateTime.now()).errorCode("200_Ok").message("unfollowed").data(profile).build();

        return ResponseEntity.ok(response);
    }
}
