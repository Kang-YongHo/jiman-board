package com.boilerplate.modules.post.domain;

import com.boilerplate.modules.account.domain.Member;
import com.boilerplate.modules.account.domain.Timestamped;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member author;

	private String content;

	@JoinColumn
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Post post;



}
