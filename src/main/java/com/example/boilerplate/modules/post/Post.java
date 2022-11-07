package com.example.boilerplate.modules.post;

import com.example.boilerplate.modules.account.domain.Timestamped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Post extends Timestamped {

//• 게시물 : 제목, 내용(HTML), 게시자, 게시 시간, 수정시간
	@Id
	private Long Id;


	// 제목
	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String author;

}
