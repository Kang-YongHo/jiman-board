package com.example.boilerplate.modules.board;

import com.example.boilerplate.modules.post.Post;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	게시판이름
	@Column(nullable = false)
	private String boardName;

	@Column(nullable = false)
	private String boardDiscription;

	@Builder.Default
	@OneToMany(mappedBy = "board")
	private List<Post> posts = new ArrayList<>();

}
