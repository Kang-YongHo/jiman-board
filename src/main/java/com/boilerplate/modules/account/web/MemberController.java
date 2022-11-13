package com.boilerplate.modules.account.web;

import com.boilerplate.modules.account.application.request.SignupRequestDto;
import com.boilerplate.modules.account.application.response.MemberResponseDto;
import com.boilerplate.modules.account.application.response.RankingResponseDto;
import com.boilerplate.modules.account.application.response.ResponseDto;
import com.boilerplate.modules.account.application.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

    @Operation(summary = "회원 전체 조회", description = "전체 회원을 조회한다.", tags = {"MemberController"})
    @GetMapping("/api/member/find-all")
    public ResponseDto<List<MemberResponseDto>> findAll(){
        return memberService.findAll();
    }

    @Operation(summary = "회원가입요청", description = "회원가입 요청을 합니다.", tags = {"MemberController"})
    @PostMapping("/api/member/signup")
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
    @GetMapping("/api/admin/signup")
    public ResponseDto<List<MemberResponseDto>> getListSignupRequest() {
        return memberService.getListSignupRequest();
    }

    @Operation(summary = "회원요청 허가", description = "관리자 계정으로 가입승인", tags = {"MemberController"})
    @PutMapping("/api/admin/activated/{memberId}")
    public ResponseDto<Boolean> activateMember(@PathVariable Long memberId) {
        return memberService.activateMember(memberId);
    }

    @Operation(summary = "회원 비활성화", description = "관리자 계정으로 가입승인", tags = {"MemberController"})
    @PutMapping("/api/admin/deactivated/{memberId}")
    public ResponseDto<Boolean> deactivateMember(@PathVariable Long memberId) {
        return memberService.deactivateMember(memberId);
    }


    @Operation(summary = "회원 50만명 가입시키기", description = "관리자 계정으로 가입", tags = {"MemberController"})
    @GetMapping("/api/admin/test-signup")
    public ResponseDto<Boolean> testSignup(){
        return memberService.testSignup();
    }

    @GetMapping("/api/test/{id}")
    public RankingResponseDto testRanking(@PathVariable Long id){
        return memberService.testRanking(id);
    }







}
