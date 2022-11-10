package com.example.boilerplate.modules.account.application.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class SignupRequestDto {

    private String email;

    private String nickname;

    private String password;

    private String admintoken;

}
