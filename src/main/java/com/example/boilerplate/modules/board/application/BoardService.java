package com.example.boilerplate.modules.board.application;

import com.example.boilerplate.modules.board.infra.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;



}
