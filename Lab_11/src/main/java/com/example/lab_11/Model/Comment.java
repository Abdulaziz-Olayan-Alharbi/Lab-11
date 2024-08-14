package com.example.lab_11.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(min = 2, max = 300)
    @Column(columnDefinition = "varchar(300) not null")
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "date")
    private LocalDate commentDate = LocalDate.now();
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer postId;




















}
