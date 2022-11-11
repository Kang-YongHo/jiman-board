package com.boilerplate.modules.board.application;

import com.boilerplate.exceptionHandler.CustomException;
import com.boilerplate.exceptionHandler.ErrorCode;
import com.boilerplate.modules.account.application.response.ResponseDto;
import com.boilerplate.modules.board.Board;
import com.boilerplate.modules.board.application.request.BoardRequestDto;
import com.boilerplate.modules.board.application.response.BoardResponseDto;
import com.boilerplate.modules.board.infra.BoardRepository;
import com.boilerplate.modules.account.domain.Member;
import com.boilerplate.modules.account.domain.RoleEnum;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;


	public ResponseDto<Boolean> create(BoardRequestDto boardRequestDto) {
		boardRepository.save(Board.builder()
			.boardName(boardRequestDto.getBoardName())
			.boardDiscription(boardRequestDto.getBoardDisc())
			.activated(true)
			.build());

		return ResponseDto.success(true);

	}

	public ResponseDto<String> blind(Long id){
		Board board = boardRepository.findById(id).orElseThrow(
			() -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
		);
		board.updateActivated(false);
		boardRepository.save(board);
		return ResponseDto.success("게시판 블러처리 완료");
	}

	public ResponseDto<List> getBoardList() {
		List<Board> boardList = boardRepository.findAll();
		List<BoardResponseDto> boardResponseDtos = new ArrayList<>();
		for(Board board:boardList){
			boardResponseDtos.add(BoardResponseDto.builder()
				.BoardName(board.getBoardName())
				.BoardDisc(board.getBoardDiscription())
				.build());
		}
		return ResponseDto.success(boardResponseDtos);

	}
}
