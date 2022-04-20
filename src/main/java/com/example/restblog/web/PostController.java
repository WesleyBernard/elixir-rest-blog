package com.example.restblog.web;


import com.example.restblog.data.Post;
import com.example.restblog.data.PostsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping( value = "/api/posts", headers = "Accept=application/json")
public class PostController {

    private PostsRepository postsRepository;

    public PostController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }


    @GetMapping
    private List<Post> getAll() {
        return postsRepository.findAll();
    }

    @GetMapping("{PostId}")
    private Post getById(@PathVariable Long PostId) {
        return postsRepository.getById(PostId);
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        Post postToAdd = new Post(newPost.getTitle(), newPost.getContent());
        postsRepository.save(postToAdd);
        System.out.println("Post created");

    }

    @PutMapping("{id}")
    private void updatePost(@RequestBody Post post, @PathVariable Long id) {
        Post updatedPost = postsRepository.getById(id);
        updatedPost.setContent(post.getContent());
        updatedPost.setTitle(post.getTitle());
        postsRepository.save(updatedPost);
        System.out.println(updatedPost);
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable Long id) {
        Post deleteMe = postsRepository.getById(id);
        postsRepository.delete(deleteMe);
        System.out.println("Deleting the post with the id of: " + id);
    }

}
