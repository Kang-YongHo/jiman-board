package com.example.boilerplate.modules.account.web;

import com.example.boilerplate.modules.account.application.MemberService;
import com.example.boilerplate.modules.account.application.request.SignupRequestDto;
import com.example.boilerplate.modules.account.application.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  //회원가입
  @PostMapping("api/member/signup")
  public ResponseDto<String> signupUser(@RequestBody SignupRequestDto requestDto) throws Exception{

    return memberService.signupUser(SignupRequestDto.builder()
            .email(requestDto.getEmail())
            .nickname(requestDto.getNickname())
            .password(requestDto.getPassword())
            .build());
  }

  //username 중복체크
  @PostMapping("/api/member/signup/checkID")
  public ResponseDto<String> checkUsername(@RequestBody SignupRequestDto requestDto) {
    return memberService.checkUsername(requestDto);
  }


}
