package com.example.lab_11.Service;

import com.example.lab_11.Api.ApiException;
import com.example.lab_11.Model.Post;
import com.example.lab_11.Repository.CategoryRepository;
import com.example.lab_11.Repository.PostRepository;
import com.example.lab_11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void addPost(Post post){
        if (!userRepository.existsById(post.getUserId())){
            throw new ApiException("User not found");
        }
        if (!categoryRepository.existsById(post.getCategoryId())){
            throw new ApiException("Category not found");
        }
        postRepository.save(post);
    }

    public void updatePost(Integer id,Post post){
        Post p = postRepository.findPostById(id);
        if (p == null){
            throw new ApiException("Post not found");
        }
        if (!userRepository.existsById(post.getUserId())){
            throw new ApiException("User not found");
        }
        if (!categoryRepository.existsById(post.getCategoryId())){
            throw new ApiException("Category not found");
        }
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        p.setCategoryId(post.getCategoryId());
        postRepository.save(p);
    }


    public void deletePost(Integer id){
        Post p = postRepository.findPostById(id);
        if (p == null){
            throw new ApiException("Post not found");
        }
        postRepository.delete(p);
    }

    public List<Post> getByCategory(Integer categoryId){
        List<Post> posts = postRepository.getByCategory(categoryId);
        if (posts == null){
            throw new ApiException("There is no posts with the gaven category");
        }
        return posts;
    }

    public List<Post> getByDate(LocalDate date1, LocalDate date2){
        List<Post> posts = postRepository.getByDateBetween(date1, date2);
        if (posts == null){
            throw new ApiException("There is no posts with the gaven dates");
        }
        return posts;
    }

    public List<Post> getPostUser(Integer userId){
        List<Post> posts = postRepository.findPostsByUserId(userId);
        if (posts == null){
            throw new ApiException("There is no posts with the gaven User Id");
        }
        return posts;
    }

    public Post getPostByTitle(String title){
        Post p =  postRepository.findPostByTitle(title);
        if (p == null){
            throw new ApiException("Post not found with the gaven title");
        }
        return p;
    }





}
