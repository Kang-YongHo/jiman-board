package com.example.boilerplate.modules.board.web;


import com.example.boilerplate.modules.board.application.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;



}
