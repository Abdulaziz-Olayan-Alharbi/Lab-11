package com.example.lab_11.Service;

import com.example.lab_11.Api.ApiException;
import com.example.lab_11.Model.Comment;
import com.example.lab_11.Repository.CommentRepository;
import com.example.lab_11.Repository.PostRepository;
import com.example.lab_11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    public void addComment(Comment comment) {
        if (!userRepository.existsById(comment.getUserId())){
            throw new ApiException("User not found");
        }
        if (!postRepository.existsById(comment.getPostId())){
            throw new ApiException("Post not found");
        }
        commentRepository.save(comment);
    }
    public void updateComment(Integer id,Comment comment) {
        Comment c = commentRepository.findCommentById(id);
        if (c == null){
            throw new ApiException("Comment not found");
        }
        c.setContent(comment.getContent());
        commentRepository.save(c);
    }

    public void deleteComment(Integer id) {
        Comment c = commentRepository.findCommentById(id);
        if (c == null){
            throw new ApiException("Comment not found");
        }
        commentRepository.delete(c);
    }

    public List<Comment> getByPostId(Integer postId) {
        List<Comment> comments = commentRepository.getCommentOfPost(postId);
        if (comments == null){
            throw new ApiException("Comment not found for post");
        }
        return comments;
    }

    public List<Comment> getCommentsByDate(LocalDate date) {
        List<Comment> comments = commentRepository.findCommentsByCommentDateAfter(date);
        if (comments == null){
            throw new ApiException("Comment not found for this month");
        }
        return comments;
    }
}
