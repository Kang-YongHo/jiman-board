package com.boilerplate.modules.board.infra;

import com.boilerplate.modules.board.domain.Board;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findAllByOrderByActivatedAsc();

	Optional<Board> findByIdAndActivatedIsTrue(Long id);

}
