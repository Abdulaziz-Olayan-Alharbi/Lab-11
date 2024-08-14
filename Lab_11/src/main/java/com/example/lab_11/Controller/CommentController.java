package com.example.lab_11.Controller;

import com.example.lab_11.Api.ApiResponse;
import com.example.lab_11.Model.Comment;
import com.example.lab_11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity getComment() {
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.addComment(comment);
        return ResponseEntity.status(201).body(new ApiResponse("Comment added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id,@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(id, comment);
        return ResponseEntity.status(201).body(new ApiResponse("Comment updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(201).body(new ApiResponse("Comment deleted successfully"));
    }

    @GetMapping("/search/post/{postId}")
    public ResponseEntity searchPost(@PathVariable Integer postId) {
        return ResponseEntity.status(200).body(commentService.getByPostId(postId));
    }

    @GetMapping("/search/{date}")
    public ResponseEntity searchDate(@PathVariable LocalDate date) {
        return ResponseEntity.status(200).body(commentService.getCommentsByDate(date));
    }


















}
