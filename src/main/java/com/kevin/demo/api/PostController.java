package com.kevin.demo.api;

import com.kevin.demo.entity.PostEntity;
import com.kevin.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public PostEntity post(@RequestBody PostEntity post) {
        return postService.createPost(post);
    }
}
