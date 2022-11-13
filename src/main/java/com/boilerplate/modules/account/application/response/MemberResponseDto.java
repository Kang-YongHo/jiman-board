package com.boilerplate.modules.account.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class MemberResponseDto {
  private Long id;
  private String nickname;
  private String email;
  private String role;
  private Long ranking;

  @Builder
  public MemberResponseDto(Long id, String nickname, String email, String role, Long ranking) {
    this.id = id;
    this.nickname = nickname;
    this.email = email;
    this.role = role;
    this.ranking = ranking;
  }
}
