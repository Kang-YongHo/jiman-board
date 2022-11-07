package com.example.boilerplate.modules.board.infra;

import com.example.boilerplate.modules.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
