package com.myblogapp.myblog.controller;

import com.myblogapp.myblog.payload.PostDto;
import com.myblogapp.myblog.payload.PostResponse;
import com.myblogapp.myblog.service.PostService;
import com.myblogapp.myblog.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){

            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
         return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false )int pageSize,
            @RequestParam(value = "sortBy",defaultValue =  AppConstants.DEFAULT_PAGE_SORT_BY, required =false)String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_PAGE_DIR, required =false) String sortDir

    ){
      return postService.getAllPosts(pageNo, pageSize, sortBy,sortDir);


    }

    //http://localhost:8080/api/post/1000
    @GetMapping("/{id}")
    public  ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto dto = postService.getPostById(id);
        return ResponseEntity.ok(postService.getPostById(id));
    }
    //http://localhost:8080/api/post/1
    @PutMapping("/{id}")
    public  ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable("id") long id){
        PostDto dto = postService.updatePost(postDto, id);
      return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    //http://localhost:8080/api/post/1
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletePost (@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post entity deleted successfully.",HttpStatus.OK);

    }
}
