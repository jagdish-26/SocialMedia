package com.Assessment.First.controller;

import com.Assessment.First.dto.SocialUserDto;
import com.Assessment.First.entity.SocialUser;
import com.Assessment.First.exception.ApiResponse;
import com.Assessment.First.exception.ResourceNotFoundException;
import com.Assessment.First.service.SocialUserService;
import com.Assessment.First.service.impl.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class SocialUserController {

    @Autowired
    private SocialUserService socialUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody SocialUserDto socialUserDto)
    {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(socialUserDto.getEmail(),socialUserDto.getPassword()));
        if(authentication.isAuthenticated())
        {
            return jwtService.generateToken(socialUserDto.getEmail());
        }
        else
        {
            return "login failed";
        }
    }

//    @GetMapping("/token")
//    public org.springframework.security.web.csrf.CsrfToken getCsrfToken(HttpServletRequest request)
//    {
//        return (org.springframework.security.web.csrf.CsrfToken) request.getAttribute("_csrf");
//    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<SocialUserDto>> registerSocialUser(@RequestBody SocialUserDto socialUserDto)
    {
        SocialUserDto userDto=socialUserService.registerSocialUser(socialUserDto);
//        ApiResponse<SocialUserDto> response = new ApiResponse<>(
//                HttpStatus.OK.value(),
//                LocalDateTime.now(),
//                "200_OK",
//                "User registered",
//                userDto
//        );

        ApiResponse<SocialUserDto> response=ApiResponse
                .<SocialUserDto>builder()
                .httpStatus(HttpStatus.OK.value())
                .timeStamp(LocalDateTime.now())
                .errorCode("200_OK")
                .message("user registered")
                .data(userDto)
                .build();
        return ResponseEntity.ok(response);

    }

    //GETMAPPING  /users/{id}-get user details based on id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SocialUserDto>> getUserDetailsById(@PathVariable("id") long userId) throws ResourceNotFoundException {
        SocialUserDto userDto = socialUserService.getUserDetailsById(userId);

//        ApiResponse<SocialUserDto> response = new ApiResponse<>(
//                HttpStatus.OK.value(),
//                LocalDateTime.now(),
//                "200_OK",
//                "User details fetched",
//                userDto
//        );
//        return ResponseEntity.ok(response);

        ApiResponse<SocialUserDto>response=ApiResponse.<SocialUserDto>builder()
                .httpStatus(HttpStatus.OK.value()).timeStamp(LocalDateTime.now()).errorCode("200_OK").message("User Details Fetched")
                .data(userDto).build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<SocialUserDto>> getUserDetailsByEmail(@PathVariable("email") String email) throws ResourceNotFoundException {
        SocialUserDto userDto = socialUserService.getUserDetailsByEmail(email);

//        ApiResponse<SocialUserDto> response = new ApiResponse<>(
//                HttpStatus.OK.value(),
//                LocalDateTime.now(),
//                "200_OK",
//                "User details fetched",
//                userDto
//        );
//        return ResponseEntity.ok(response);

        ApiResponse<SocialUserDto>response=ApiResponse.<SocialUserDto>builder()
                .httpStatus(HttpStatus.OK.value())
                .timeStamp(LocalDateTime.now())
                .errorCode("200_OK")
                .message("User Details Fetched")
                .data(userDto).build();
        return ResponseEntity.ok(response);
    }

    //PUT /users/updateUser/{id}--update user Details
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ApiResponse<SocialUserDto>>updateSocialUser(
            @PathVariable("id") long userId, @RequestBody SocialUserDto socialUserDto)
            throws ResourceNotFoundException {
        SocialUserDto userDto1 =socialUserService.updateSocialUser(userId,socialUserDto);
//        ApiResponse<SocialUserDto> response = new ApiResponse<>(
//                HttpStatus.OK.value(),
//                LocalDateTime.now(),
//                "200_OK",
//                "User details updated",
//                userDto1
//        );
//        return ResponseEntity.ok(response);

        ApiResponse<SocialUserDto>response=ApiResponse.<SocialUserDto>builder()
                .httpStatus(HttpStatus.OK.value()).timeStamp(LocalDateTime.now()).errorCode("200_OK").message("User Details updated").data(userDto1).build();
        return  ResponseEntity.ok(response);

    }


    //GETMAPPING /user/getAllUsers---get all users use pagination here
    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponse<Page<SocialUser>>> getAllUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "userId") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {

        Page<SocialUser> userPage = socialUserService.getAllUsers(page, size, sortBy, ascending);
//        ApiResponse<Page<SocialUser>> response = new ApiResponse<>(
//                HttpStatus.OK.value(),
//                LocalDateTime.now(),
//                "200_OK",
//                "Fetched users successfully",
//                userPage
//        );

        ApiResponse<Page<SocialUser>> response=ApiResponse.<Page<SocialUser>>builder()
                .httpStatus(HttpStatus.OK.value()).timeStamp(LocalDateTime.now()).errorCode("200_OK").message("Fetched users successfully")
                .data(userPage).build();

        return ResponseEntity.ok(response);
    }


}
