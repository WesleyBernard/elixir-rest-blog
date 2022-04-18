package com.example.restblog.web;


import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping( value = "/api/posts", headers = "Accept=application/json")
public class PostController {

    @GetMapping
    private List<Post> getAll() {
        ArrayList<Post> dummyPosts = new ArrayList<>();

        dummyPosts.add(new Post(1L, "Post one", "You know how we do it here"));
        dummyPosts.add(new Post(2L, "Post dos", "omegalol"));
        dummyPosts.add(new Post(3L, "Post three, but in another language", "Elixir? More like a mixer. Because we're all friends not because of like anything weird"));

        return dummyPosts;

    }

    @GetMapping("{PostId}")
    public Post getById(@PathVariable Long PostId) {
        return new Post(PostId, "This is your fake post", "Wesley is really really cool");
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        System.out.println("Adding post...." + newPost);

    }

    @PutMapping("{id}")
    private void updatePost(@RequestBody Post post, @PathVariable Long id) {
        Post updatedPost = new Post();
        updatedPost.setContent(post.getContent());
        updatedPost.setTitle(post.getTitle());
        updatedPost.setId(id);
        System.out.println(updatedPost);
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable Long id) {
        System.out.println("Deleting the post with the id of: " + id);

    }



}
