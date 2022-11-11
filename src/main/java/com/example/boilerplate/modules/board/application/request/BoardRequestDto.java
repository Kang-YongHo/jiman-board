package com.example.boilerplate.modules.board.application.request;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class BoardRequestDto {

	private String BoardName;
	private String BoardDisc;

}
