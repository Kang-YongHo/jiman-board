package com.example.boilerplate.modules.board.web;

import com.example.boilerplate.modules.account.application.response.ResponseDto;
import com.example.boilerplate.modules.board.application.BoardService;
import com.example.boilerplate.modules.board.application.request.BoardRequestDto;
import com.example.boilerplate.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

	private final BoardService boardService;

	//	게시판 추가
 	@PostMapping("")
	public ResponseDto<Boolean> createBoard(@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestBody BoardRequestDto boardRequestDto){

		 return boardService.create(userDetails.getMember(),boardRequestDto);
	}

	//게시판 블라인드
	@PutMapping("/{boardId}")
	public ResponseDto<String> blind(@AuthenticationPrincipal UserDetailsImpl userDetails,
		@PathVariable Long boardId){
		 return boardService.blind(userDetails.getMember(),boardId);
	}

	// 게시판 리스트 (조회가능 여부)



}
