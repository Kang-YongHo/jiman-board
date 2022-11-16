package com.boilerplate.modules.post.application.request;

import com.boilerplate.modules.account.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class PostRequestDto {
	public Long id;

	public Member writer;

	public String content;

}
