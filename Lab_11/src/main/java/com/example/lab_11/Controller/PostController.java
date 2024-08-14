package com.example.lab_11.Controller;


import com.example.lab_11.Api.ApiResponse;
import com.example.lab_11.Model.Post;
import com.example.lab_11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity getAllPost(){
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("Post added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id,@Valid @RequestBody Post post, Errors errors ){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.updatePost(id, post);
        return ResponseEntity.status(200).body(new ApiResponse("Post updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post deleted successfully"));
    }

    @GetMapping("/search/category/{categoryId}")
    public ResponseEntity getPostsByCategory(@PathVariable Integer categoryId){
        return ResponseEntity.status(200).body(postService.getByCategory(categoryId));
    }

    @GetMapping("/search/date/{date1}/{date2}")
    public ResponseEntity getPostsByDate(@PathVariable LocalDate date1, @PathVariable LocalDate date2){
        return ResponseEntity.status(200).body(postService.getByDate(date1, date2));
    }

    @GetMapping("/search/user/{userId}")
    public ResponseEntity getPostsByUserId(@PathVariable Integer userId){
        return ResponseEntity.status(200).body(postService.getPostUser(userId));
    }

    @GetMapping("/search/title/{title}")
    public ResponseEntity getPostsByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(postService.getPostByTitle(title));
    }










}
