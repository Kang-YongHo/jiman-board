package com.boilerplate.modules.account.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.boilerplate.common.MockMvcTest;
import com.boilerplate.modules.account.application.MemberService;
import com.boilerplate.modules.account.application.request.SignupRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@MockMvcTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberService memberService;


    @BeforeEach
    void setUp() {
        SignupRequestDto build = SignupRequestDto.builder()
            .email("test@test.com")
            .nickname("test")
            .admintoken("ADMIN_TOKEN")
            .password("test12341234")
            .build();
        memberService.signupUser(build);
    }

    @Test
    @DisplayName("회원 조회")
    void findAll_member() throws Exception {
        //given

        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/member/find-all"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        //then
        Assertions.assertNotNull(mvcResult.getResponse());
    }
}