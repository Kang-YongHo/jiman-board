package com.example.boilerplate.modules.account.application.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
  private Long id;
  private String name;
  private String email;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
