package com.myblogapp.myblog.service;

import com.myblogapp.myblog.payload.PostDto;
import com.myblogapp.myblog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo , int  pageSize , String sortBy, String sortDir);


    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);
}
