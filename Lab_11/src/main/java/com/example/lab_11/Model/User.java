package com.example.lab_11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(min = 4 , max = 18)
    @Column(columnDefinition = "varchar(18) not null unique")
    private String username;
    @NotEmpty
    @Size(min = 8 , max = 18)
    @Column(columnDefinition = "varchar(18) not null")
    private String password;
    @NotEmpty
    @Email
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "date")
    private LocalDate registrationDate = LocalDate.now();
}
