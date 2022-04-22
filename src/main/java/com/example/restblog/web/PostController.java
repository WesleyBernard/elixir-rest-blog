package com.example.restblog.web;


import com.example.restblog.data.*;
import com.example.restblog.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping( value = "/api/posts", headers = "Accept=application/json")
public class PostController {

    private PostsRepository postsRepository;
    private UsersRepository usersRepository;
    private CategoriesRepository categoriesRepository;
    private EmailService emailService;


    @GetMapping
    private List<Post> getAll() {
        return postsRepository.findAll();
    }

    @GetMapping("{PostId}")
    private Optional<Post> getById(@PathVariable Long PostId) {
        return postsRepository.findById(PostId);
    }

    @GetMapping("byCategory")
    private List<Post>getByCategory(@RequestParam(name = "category") String category) {
        return postsRepository.findByCategories(categoriesRepository.findCategoryByName(category));
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(categoriesRepository.findCategoryByName("Gaming"));
        categories.add(categoriesRepository.findCategoryByName("Node"));
        newPost.setAuthor(usersRepository.getById(1L));
        newPost.setCategories(categories);
        postsRepository.save(newPost);
        System.out.println("Post created");

        emailService.prepareAndSend(newPost, "Hello, Loser", "This is me making sure your dumb email works");

    }

    @PutMapping("{id}")
    private void updatePost(@RequestBody Post post, @PathVariable Long id) {
        Post updatedPost = postsRepository.getById(id);
        updatedPost.setContent(post.getContent());
        updatedPost.setTitle(post.getTitle());
        postsRepository.save(updatedPost);
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable Long id) {
        Post deleteMe = postsRepository.getById(id);
        postsRepository.delete(deleteMe);
        System.out.println("Deleting the post with the id of: " + id);
    }

}
