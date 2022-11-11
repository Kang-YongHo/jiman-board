package com.example.boilerplate.modules.board.application;

import com.example.boilerplate.exceptionHandler.CustomException;
import com.example.boilerplate.exceptionHandler.ErrorCode;
import com.example.boilerplate.modules.account.application.response.ResponseDto;
import com.example.boilerplate.modules.account.domain.Member;
import com.example.boilerplate.modules.account.domain.RoleEnum;
import com.example.boilerplate.modules.board.Board;
import com.example.boilerplate.modules.board.application.request.BoardRequestDto;
import com.example.boilerplate.modules.board.infra.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;


	public ResponseDto<Boolean> create(Member member, BoardRequestDto boardRequestDto) {
		checkAdmin(member);
		boardRepository.save(Board.builder()
			.boardName(boardRequestDto.getBoardName())
			.boardDiscription(boardRequestDto.getBoardDisc())
			.activated(true)
			.build());

		return ResponseDto.success(true);

	}

	public ResponseDto<String> blind(Member member, Long id){
		Board board = boardRepository.findById(id).orElseThrow(
			() -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
		);
		checkAdmin(member);
		board.updateActivated(false);
		boardRepository.save(board);
		return ResponseDto.success("게시판 블러처리 완료");
	}

	public void checkAdmin(Member member) {
		if (!member.getRole().equals(RoleEnum.ADMIN)) {
			throw new CustomException(ErrorCode.INVALID_AUTHORITY);
		}
	}
}
