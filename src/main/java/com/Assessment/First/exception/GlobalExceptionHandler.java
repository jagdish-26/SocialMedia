package com.Assessment.First.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception) {
//        ApiResponse<?> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(),
//                LocalDateTime.now(), "RESOURCE_NOT_FOUND", exception.getMessage(), null);

        ApiResponse<?>response=ApiResponse.builder()
                                          .httpStatus(HttpStatus.NOT_FOUND.value())
                                          .timeStamp(LocalDateTime.now())
                                          .errorCode("Resource_Not_Found")
                                          .message(exception.getMessage())
                                           .data(null)
                                          .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception) {
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(), "INTERNAL_SERVER_ERROR", exception.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
