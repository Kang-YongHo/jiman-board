package com.boilerplate.modules.post.application.response;

import com.boilerplate.modules.account.domain.Member;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Builder
public class PostResponseDto {
	public Long id;

	public Member writer;

	public String title;

	public String content;

	public LocalDateTime createdAt;

	public LocalDateTime modifiedAt;

}
