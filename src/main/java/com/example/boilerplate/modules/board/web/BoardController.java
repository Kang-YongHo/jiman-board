package com.example.boilerplate.modules.board.web;

import com.example.boilerplate.modules.account.application.response.ResponseDto;
import com.example.boilerplate.modules.board.application.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

	private final BoardService boardService;

//	게시판 추가
 	@PostMapping("")
	public ResponseDto<Boolean> createBoard(){

		 //로직
		 return ResponseDto.success(true);
	}


}
