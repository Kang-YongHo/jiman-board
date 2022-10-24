package com.example.boilerplate.security.provider;

import com.example.boilerplate.exceptionHandler.CustomException;
import com.example.boilerplate.exceptionHandler.ErrorCode;
import com.example.boilerplate.modules.account.domain.Member;
import com.example.boilerplate.modules.account.infra.MemberRepository;
import com.example.boilerplate.security.UserDetailsImpl;
import com.example.boilerplate.security.jwt.JwtDecoder;
import com.example.boilerplate.security.jwt.JwtPreProcessingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTAuthProvider implements AuthenticationProvider {

    private final JwtDecoder jwtDecoder;
    private final MemberRepository memberRepository;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        String email = jwtDecoder.decodeUsername(token);

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        UserDetailsImpl userDetails = new UserDetailsImpl(member);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
