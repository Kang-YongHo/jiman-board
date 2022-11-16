package com.boilerplate.modules.post.application;

import com.boilerplate.modules.account.application.response.ResponseDto;
import com.boilerplate.modules.post.application.response.PostResponseDto;
import com.boilerplate.modules.post.domain.Post;
import com.boilerplate.modules.post.infra.PostRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public ResponseDto<List<PostResponseDto>> getPostList() {

		List<Post> posts = postRepository.findAllByActivated(true);
		List<PostResponseDto> response = new ArrayList<>();
		for (Post post : posts) {
			response.add(PostResponseDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.writer(post.getWriter())
				.createdAt(post.getCreatedAt())
				.modifiedAt(post.getModifiedAt())
				.build());

		}
		return ResponseDto.success(response);
	}

}
