package com.beyond.mvc.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;


public record UserResponseDto(int no, String username, @JsonIgnore String address) {

}
