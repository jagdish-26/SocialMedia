package com.Assessment.First.dto;

import com.Assessment.First.entity.SocialUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;
    private String title;
    private String author;
    private String tag;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createDat;

}

