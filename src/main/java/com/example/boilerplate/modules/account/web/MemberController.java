package com.example.boilerplate.modules.account.web;

import com.example.boilerplate.modules.account.application.MemberService;
import com.example.boilerplate.modules.account.application.request.SignupRequestDto;
import com.example.boilerplate.modules.account.application.response.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "회원가입을 합니다.", tags = {"MemberController"})
    @PostMapping("api/member/signup")
    public ResponseDto<String> signupUser(@RequestBody SignupRequestDto requestDto)
        throws Exception {

        return memberService.signupUser(SignupRequestDto.builder()
            .email(requestDto.getEmail())
            .nickname(requestDto.getNickname())
            .password(requestDto.getPassword())
            .build());
    }

    @Operation(summary = "유저네임 중복 체크", description = "회원가입 시 유저네임이 중복되는지 확인합니다.", tags = {"MemberController"})
    @PostMapping("/api/member/signup/check-id")
    public ResponseDto<String> checkUsername(@RequestBody SignupRequestDto requestDto) {
        return memberService.checkUsername(requestDto);
    }


}
