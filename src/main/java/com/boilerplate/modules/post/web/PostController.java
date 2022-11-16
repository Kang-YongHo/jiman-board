package com.boilerplate.modules.post.web;

import com.boilerplate.modules.account.application.response.ResponseDto;
import com.boilerplate.modules.post.application.PostService;
import com.boilerplate.modules.post.application.request.PostRequestDto;
import com.boilerplate.modules.post.application.response.PostResponseDto;
import com.boilerplate.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;


	@GetMapping("/api/post/{boardId}")
	public ResponseDto<List<PostResponseDto>> getPostList(@PathVariable Long boardId){
		return postService.getPostList(boardId);
	}

	@PostMapping("/api/post/{boardId}")
	public ResponseDto<Boolean> postBoard(@PathVariable Long boardId,
		@RequestBody PostRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails){
		return postService.postBoard(boardId, requestDto, userDetails);
	}
}
