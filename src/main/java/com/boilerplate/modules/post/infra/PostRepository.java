package com.boilerplate.modules.post.infra;

import com.boilerplate.modules.board.domain.Board;
import com.boilerplate.modules.post.domain.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

	Optional<Post> findById(Long id);

	List<Post> findAllByActivatedAndBoard(Boolean activated, Board board);
}
