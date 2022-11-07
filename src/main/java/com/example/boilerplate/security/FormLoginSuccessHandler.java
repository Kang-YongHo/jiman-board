package com.example.boilerplate.security;

import com.example.boilerplate.modules.account.application.response.LoginResponseDto;
import com.example.boilerplate.modules.account.domain.Member;
import com.example.boilerplate.security.jwt.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";
    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {
        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
        // Token 생성
        final String token = JwtTokenUtils.generateJwtToken(userDetails);
        System.out.println(userDetails.getUsername() + "'s token : " + TOKEN_TYPE + " " + token);
        response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + token);
        System.out.println("LOGIN SUCCESS!");

        //Member 정보 프론트 전달
        response.setContentType("application/json; charset=utf-8");
        Member member = userDetails.getMember();
        LoginResponseDto loginResponseDto = new LoginResponseDto(member.getId(), member.getName(), true, token);

        String result = mapper.writeValueAsString(loginResponseDto);
        response.getWriter().write(result);
    }

}
