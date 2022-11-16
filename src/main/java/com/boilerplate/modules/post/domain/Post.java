package com.boilerplate.modules.post.domain;

import com.boilerplate.modules.account.domain.Member;
import com.boilerplate.modules.account.domain.Timestamped;
import com.boilerplate.modules.board.domain.Board;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

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

	@ManyToOne(fetch = FetchType.LAZY)
	private Member writer;

	@ManyToOne
	@JoinColumn(name = "board")
	private Board board;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments;

	private Boolean ativated;


}
