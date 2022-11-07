package com.example.boilerplate.modules.account.application.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {
    //로그인 시 body로 내려가는 사용자 정보

    private Long userId;

    private String name;

    private boolean login;

    private String accessToken;

    //일반 로그인할 때 프론트에 내려주는 값
    public LoginResponseDto(Long userId, String name, boolean login, String accessToken) {
        this.userId = userId;
        this.name = name;
        this.login = login;  //login true/ false 상황
        this.accessToken = accessToken;
    }
}
