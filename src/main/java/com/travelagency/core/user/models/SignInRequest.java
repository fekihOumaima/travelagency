package com.ditraacademy.travelagency.core.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SignInRequest {
    private String username;
    private String password;
}
