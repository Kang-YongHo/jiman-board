package com.boilerplate.modules.post;

import com.boilerplate.modules.account.domain.Timestamped;
import com.boilerplate.modules.board.Board;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Timestamped {

//• 게시물 : 제목, 내용(HTML), 게시자, 게시 시간, 수정시간
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;


	// 제목
	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String author;

	@ManyToOne
	@JoinColumn(name = "board")
	private Board board;
}
