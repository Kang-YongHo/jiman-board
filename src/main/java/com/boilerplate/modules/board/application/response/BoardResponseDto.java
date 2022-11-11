package com.boilerplate.modules.board.application.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class BoardResponseDto {
	private String BoardName;
	private String BoardDisc;
}
