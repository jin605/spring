package com.beyond.university.auth.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int no;

    private String username;

    private String password;

    private String role;

    private String nickname;

    private String phone;

    private String email;

    private String address;

    private String hobby;

    private String status;

    private LocalDate createdAt;

    private LocalDate updatedAt;

}