package com.myblogapp.myblog.controller;

import com.myblogapp.myblog.payload.CommentDto;
import com.myblogapp.myblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //localhost:8080/api/posts/1/comments
    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable("postId") long postid,
            @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postid, commentDto);
       return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
    //localhost:8080/api/posts/1/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllCommentsByPostId(@PathVariable("postId") Long postId){
        List<CommentDto> dto = commentService.getCommentByPostId(postId);
        return  dto;
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(
                 @PathVariable("postId") long postId,
                 @PathVariable("id") long id,
                 @RequestBody CommentDto commentDto

    ){


        CommentDto dto = commentService.updateComment(postId, id, commentDto);

        return new  ResponseEntity<>(dto, HttpStatus.OK);

    }
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("postId")long postId,
            @PathVariable("id") long commentId

    ){
        commentService.deleteComment(postId, commentId);
        return  new ResponseEntity<>("comment is delete!", HttpStatus.OK);

    }

}
