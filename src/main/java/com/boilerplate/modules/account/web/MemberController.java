package com.boilerplate.modules.account.web;

import com.boilerplate.modules.account.application.request.SignupRequestDto;
import com.boilerplate.modules.account.application.response.ResponseDto;
import com.boilerplate.security.UserDetailsImpl;
import com.boilerplate.modules.account.application.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입요청", description = "회원가입 요청을 합니다.", tags = {"MemberController"})
    @PostMapping("api/member/signup")
    public ResponseDto<String> signupUser(@RequestBody SignupRequestDto requestDto)
        throws Exception {

        return memberService.signupUser(requestDto);
    }

    @Operation(summary = "유저네임 중복 체크", description = "회원가입 시 유저네임이 중복되는지 확인합니다.", tags = {"MemberController"})
    @PostMapping("/api/member/signup/check-id")
    public ResponseDto<String> checkUsername(@RequestBody SignupRequestDto requestDto) {
        return memberService.checkUsername(requestDto);
    }


    @Operation(summary = "회원요청 리스트 보기", description = "관리자 계정으로 가입요청한 회원리스트를 가져옵니다", tags = {"MemberController"})
    @GetMapping("/api/member/signup/list")
    public ResponseDto<List> getListSignupRequest(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memberService.getListSignupRequest(userDetails.getMember());
    }

    @Operation(summary = "회원요청 허가", description = "관리자 계정으로 가입승인", tags = {"MemberController"})
    @PutMapping("/api/member/{memberId}")
    public ResponseDto<Boolean> activateMember(@PathVariable Long memberId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memberService.activateMember(userDetails.getMember(),memberId);
    }




}
