package com.kevin.demo.service;

import com.kevin.demo.entity.PostEntity;
import com.kevin.demo.persist.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostEntity createPost(PostEntity request) {
        return postRepository.save(request);
    }
}
