package com.Assessment.First.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String tag;
    private String content;
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createDat;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private SocialUser user;
}
