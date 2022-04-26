package com.example.restblog.web;


import com.example.restblog.data.*;
import com.example.restblog.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
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
    public List<Post> getAll() {
        return postsRepository.findAll();
    }

    @GetMapping("{PostId}")
    public Optional<Post> getById(@PathVariable Long PostId) {
        return postsRepository.findById(PostId);
    }

    @GetMapping("byCategory")
    public List<Post>getByCategory(@RequestParam(name = "category") String category) {
        return postsRepository.findByCategories(categoriesRepository.findCategoryByName(category));
    }

    @PostMapping
    public void createPost(@RequestBody Post newPost, OAuth2Authentication auth) {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(categoriesRepository.findCategoryByName("Development"));
        categories.add(categoriesRepository.findCategoryByName("Java"));
        newPost.setAuthor(usersRepository.findByEmail(auth.getName()));
        newPost.setCategories(categories);
        postsRepository.save(newPost);
        System.out.println("Post created");

        emailService.prepareAndSend(newPost, "Hello, Loser", "This is me making sure your dumb email works");

    }

    @PutMapping("{id}")
    public void updatePost(@RequestBody Post post, @PathVariable Long id) {
        Post updatedPost = postsRepository.getById(id);
        updatedPost.setContent(post.getContent());
        updatedPost.setTitle(post.getTitle());
        postsRepository.save(updatedPost);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN') || @postsRepository.getById(#id).getAuthor().getEmail().equals(#auth.getName())")
    public void deletePost(@PathVariable Long id, OAuth2Authentication auth) {
            System.out.println(auth.getAuthorities());
            postsRepository.deleteById(id);
            System.out.println("Deleting the post with the id of: " + id);
        }
}
