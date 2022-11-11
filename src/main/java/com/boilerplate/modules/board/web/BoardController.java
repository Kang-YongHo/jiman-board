package com.boilerplate.modules.board.web;

import com.boilerplate.modules.account.application.response.ResponseDto;
import com.boilerplate.modules.board.application.request.BoardRequestDto;
import com.boilerplate.security.UserDetailsImpl;
import com.boilerplate.modules.board.application.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/board")
public class BoardController {

	private final BoardService boardService;

	//	게시판 추가
 	@PostMapping("")
	public ResponseDto<Boolean> createBoard(@RequestBody BoardRequestDto boardRequestDto){

		 return boardService.create(boardRequestDto);
	}

	@GetMapping("")
	public ResponseDto<List> getBoardList(){
		 return boardService.getBoardList();
	}

	//게시판 블라인드
	@PutMapping("/{boardId}")
	public ResponseDto<String> blind(
		@PathVariable Long boardId){
		 return boardService.blind(boardId);
	}

	// 게시판 리스트 (조회가능 여부)



}
