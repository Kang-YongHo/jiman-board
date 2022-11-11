package com.boilerplate.modules.account.application;

import com.boilerplate.exceptionHandler.CustomException;
import com.boilerplate.exceptionHandler.ErrorCode;
import com.boilerplate.modules.account.application.request.SignupRequestDto;
import com.boilerplate.modules.account.application.response.ResponseDto;
import com.boilerplate.modules.account.domain.Member;
import com.boilerplate.modules.account.domain.RoleEnum;
import com.boilerplate.modules.account.infra.MemberRepository;
import java.util.List;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.admin.token}") // 어드민 가입용
    String ADMIN_TOKEN;
    String passwordPattern = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}"; //영어, 숫자 8자이상 20이하
    String emailPattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"; //이메일 정규식 패턴
    String nicknamePattern = "^[a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣~!@#$%^&*]{2,8}"; //닉네임 정규식 패턴

    public ResponseDto<String> signupUser(SignupRequestDto requestDto) {

		checkEmailVal(requestDto.getEmail());
		checkNickVal(requestDto.getNickname());
		checkPassVal(requestDto.getPassword());

        RoleEnum role = RoleEnum.USER;
        if(ADMIN_TOKEN.equals(requestDto.getAdmintoken())){
            role = RoleEnum.ADMIN;
        }

        String password = passwordEncoder.encode(requestDto.getPassword()); // 패스워드 암호화
        Member member = Member.builder()
                .email(requestDto.getEmail())
                .nickname(requestDto.getNickname())
                .password(password)
                .role(role)
                .activated(false)
                .build();
        memberRepository.save(member);

        return ResponseDto.success("회원가입에 성공하였습니다.");
    }

    //username 중복체크
    public ResponseDto<String> checkUsername(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String emailPattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; //이메일 정규식 패턴

        //username 정규식 맞지 않는 경우 오류메시지 전달
        if(email.equals(""))
            throw new CustomException(ErrorCode.EMPTY_USERNAME);
        else if (!Pattern.matches(emailPattern, email))
            throw new CustomException(ErrorCode.USERNAME_WRONG);
        else if (memberRepository.findByEmail(email).isPresent())
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);

        return ResponseDto.success("사용할 수 있는 이메일입니다.");
    }

    public void checkEmailVal (String email){
        if(email.equals(""))
            throw new CustomException(ErrorCode.EMPTY_USERNAME);
        else if (!Pattern.matches(emailPattern, email))
            throw new CustomException(ErrorCode.USERNAME_WRONG);
        else if (memberRepository.findByEmail(email).isPresent())
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);

    }
    //username 정규식 맞지 않는 경우 오류메시지 전달

    public void checkNickVal(String nickname) {
        //nickname 정규식 맞지 않는 경우 오류메시지 전달
        if (nickname.equals(""))
            throw new CustomException(ErrorCode.EMPTY_NICKNAME);
        else if (2 > nickname.length() || 8 < nickname.length())
            throw new CustomException(ErrorCode.NICKNAME_LEGNTH);
        else if (!Pattern.matches(nicknamePattern, nickname))
            throw new CustomException(ErrorCode.NICKNAME_WRONG);

    }

	public void checkPassVal(String password){
    //password 정규식 맞지 않는 경우 오류메시지 전달
        if(password.equals(""))
            throw new CustomException(ErrorCode.EMPTY_PASSWORD);
        else if ( 8 > password.length() || 20 < password.length() )
			throw new CustomException(ErrorCode.PASSWORD_LEGNTH);
        else if (!Pattern.matches(passwordPattern, password))
			throw new CustomException(ErrorCode.PASSWORD_WRONG);

	}

    public ResponseDto<List> getListSignupRequest(Member member) {
        checkAdmin(member);
        List<Member> memberList = memberRepository.findAllByActivatedIsFalse();

        return ResponseDto.success(memberList);
    }

    public ResponseDto<Boolean> activateMember(Member member, Long Id){
        checkAdmin(member);
        Member account = memberRepository.findById(Id).orElseThrow(
            () -> new CustomException(ErrorCode.USER_NOT_FOUND));

        account.updateActivated(true);
        memberRepository.save(account);

        return ResponseDto.success(true);
    }

    public void checkAdmin(Member member){
        if(!member.getRole().equals(RoleEnum.ADMIN)){
            throw new CustomException(ErrorCode.INVALID_AUTHORITY);
        }
    }




}
