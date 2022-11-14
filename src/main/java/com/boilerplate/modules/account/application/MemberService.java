package com.boilerplate.modules.account.application;

import com.boilerplate.exceptionHandler.CustomException;
import com.boilerplate.exceptionHandler.ErrorCode;
import com.boilerplate.modules.account.application.request.SignupRequestDto;
import com.boilerplate.modules.account.application.response.MemberResponseDto;
import com.boilerplate.modules.account.application.response.RankingResponseDto;
import com.boilerplate.modules.account.application.response.ResponseDto;
import com.boilerplate.modules.account.domain.Member;
import com.boilerplate.modules.account.domain.RoleEnum;
import com.boilerplate.modules.account.infra.MemberJdbcRepository;
import com.boilerplate.modules.account.infra.MemberRepository;
import com.boilerplate.modules.account.infra.RankingInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final MemberJdbcRepository repository;

	private final ModelMapper modelMapper;

	@Value("${spring.admin.token}") // 어드민 가입용
	String ADMIN_TOKEN;
	String passwordPattern = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}"; //영어, 숫자 8자이상 20이하
	String emailPattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"; //이메일 정규식 패턴
	String nicknamePattern = "^[a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣~!@#$%^&*]{2,8}"; //닉네임 정규식 패턴

	public ResponseDto<String> signupUser(SignupRequestDto requestDto) {

		checkEmailVal(requestDto.getEmail());
		checkNickVal(requestDto.getNickname());
		checkPassVal(requestDto.getPassword());

		RoleEnum role = RoleEnum.DEACTIVATED_USER;
		String mention = "회원가입요청 되었습니다.";
		if (ADMIN_TOKEN.equals(requestDto.getAdmintoken())) {
			role = RoleEnum.ADMIN;
			mention = "관리자로 가입되셨습니다.";
			System.out.println(role);
		}

		String password = passwordEncoder.encode(requestDto.getPassword()); // 패스워드 암호화
		Member member = Member.builder()
			.email(requestDto.getEmail())
			.nickname(requestDto.getNickname())
			.password(password)
			.role(role)
			.point(10L)
			.build();
		memberRepository.save(member);

		return ResponseDto.success(mention);
	}

	//username 중복체크
	public ResponseDto<String> checkUsername(SignupRequestDto requestDto) {
		String email = requestDto.getEmail();
		String emailPattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; //이메일 정규식 패턴

		//username 정규식 맞지 않는 경우 오류메시지 전달
		if (email.equals("")) {
			throw new CustomException(ErrorCode.EMPTY_USERNAME);
		} else if (!Pattern.matches(emailPattern, email)) {
			throw new CustomException(ErrorCode.USERNAME_WRONG);
		} else if (memberRepository.findByEmail(email).isPresent()) {
			throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
		}

		return ResponseDto.success("사용할 수 있는 이메일입니다.");
	}

	public void checkEmailVal(String email) {
		if (email.equals("")) {
			throw new CustomException(ErrorCode.EMPTY_USERNAME);
		} else if (!Pattern.matches(emailPattern, email)) {
			throw new CustomException(ErrorCode.USERNAME_WRONG);
		} else if (memberRepository.findByEmail(email).isPresent()) {
			throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
		}

	}
	//username 정규식 맞지 않는 경우 오류메시지 전달

	public void checkNickVal(String nickname) {
		//nickname 정규식 맞지 않는 경우 오류메시지 전달
		if (nickname.equals("")) {
			throw new CustomException(ErrorCode.EMPTY_NICKNAME);
		} else if (2 > nickname.length() || 8 < nickname.length()) {
			throw new CustomException(ErrorCode.NICKNAME_LEGNTH);
		} else if (!Pattern.matches(nicknamePattern, nickname)) {
			throw new CustomException(ErrorCode.NICKNAME_WRONG);
		} else if (memberRepository.findByNickname(nickname).isPresent()) {
			throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
		}

	}

	public void checkPassVal(String password) {
		//password 정규식 맞지 않는 경우 오류메시지 전달
		if (password.equals("")) {
			throw new CustomException(ErrorCode.EMPTY_PASSWORD);
		} else if (8 > password.length() || 20 < password.length()) {
			throw new CustomException(ErrorCode.PASSWORD_LEGNTH);
		} else if (!Pattern.matches(passwordPattern, password)) {
			throw new CustomException(ErrorCode.PASSWORD_WRONG);
		}

	}

	public ResponseDto<List<MemberResponseDto>> getListSignupRequest() {
		System.out.println(memberRepository.findRankingById(1L));
		System.out.println(memberRepository.findRankingById(2L));
		System.out.println(memberRepository.findRankingById(3L));
		List<Member> memberList = memberRepository.findAllByRole(RoleEnum.DEACTIVATED_USER);
		List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
		for (Member member : memberList) {
			memberResponseDtos.add(MemberResponseDto.builder()
				.id(member.getId())
				.email(member.getEmail())
				.nickname(member.getNickname())
				.role(member.getRole().toString())

				.build());
		}
		return ResponseDto.success(memberResponseDtos);
	}

	public ResponseDto<Boolean> activateMember(Long id) {
		Member member = memberRepository.findById(id).orElseThrow(
			() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		if (member.getRole().equals(RoleEnum.ACTIVATED_USER)) {
			ResponseDto.fail("400", "이미 활성화 된 회원입니다.");
		}
		member.updateRole(RoleEnum.ACTIVATED_USER);
		memberRepository.save(member);
		return ResponseDto.success(true);
	}


	public ResponseDto<Boolean> deactivateMember(Long id) {
		Member member = memberRepository.findById(id).orElseThrow(
			() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		if (member.getRole().equals(RoleEnum.DEACTIVATED_USER)) {
			ResponseDto.fail("400", "이미 비활성화 된 회원입니다.");
		}
		member.updateRole(RoleEnum.DEACTIVATED_USER);
		memberRepository.save(member);
		return ResponseDto.success(true);
	}

	public ResponseDto<Boolean> testSignup() {
		long beforeTime =System.currentTimeMillis();
		String password = passwordEncoder.encode("qwer1234");
		List<Member> memberList = new ArrayList<>();

		for (int i = 0; i < 500000; i++) {
			long random = (long) (Math.random() * (999999 - 100000 + 1)) + 100000;
			String email = "a";
			String num = String.valueOf(i);
			email += "0".repeat(6 - num.length()) + num;
			Member member = Member.builder()
				.email(email + "@nate.com")
				.nickname(email)
				.password(password)
				.role(RoleEnum.ACTIVATED_USER)
				.point(random)
				.build();
			memberList.add(member);
		}
		memberRepository.saveAll(memberList);
//		repository.saveAll(memberList);	jdbc bulk insert
		long afterTime= System.currentTimeMillis();
		long secDiffTime = (afterTime-beforeTime)/1000;
		System.out.println("시간차이(m) : "+secDiffTime);

		return ResponseDto.success(true);
	}

	public RankingResponseDto testRanking(Long id) {
		Member member = memberRepository.findById(id).orElseThrow(
			() -> new CustomException(ErrorCode.USER_NOT_FOUND)
		);
		RankingInterface ranking = memberRepository.findRankingById(id);

		member.updateRanking(ranking.getRanking());
		memberRepository.save(member);
		return RankingResponseDto.builder()
			.id(ranking.getId())
			.ranking(ranking.getRanking())
			.build();
	}

	public ResponseDto<List<MemberResponseDto>> findAll() {
		return ResponseDto.success(memberRepository.findAll(PageRequest.of(0, 10000))
			.stream()
			.map(member -> modelMapper.map(member, MemberResponseDto.class))
			.collect(Collectors.toList()));
	}
}
