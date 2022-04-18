package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/users", headers = "Accept=application/json")
public class UsersController {

    @GetMapping
    private List<User> getAll(){
        ArrayList<User> dummies = new ArrayList<>();
        User wesley = new User(0, "BlackFidelis", "UwU@gmail.com", "123", LocalDate.now(), User.Role.ADMIN);
        dummies.add(wesley);
        return dummies;
    }

    @GetMapping("{id}")
    private User getById(@PathVariable long id){
        return new User(id, "BlackFidelis", "UwU@gmail.com", "123", LocalDate.now(), User.Role.ADMIN);
    }

    @PostMapping
    private void createUser(@RequestBody User newUser) {
        System.out.println("Adding user...." + newUser);
    }

    @PutMapping("{id}")
    private void updateUser(@RequestBody User newUser, @PathVariable long id) {
        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setUsername(newUser.getUsername());
        updatedUser.setEmail(newUser.getEmail());
        updatedUser.setPassword(newUser.getPassword());
        updatedUser.setDate(newUser.getDate());
        updatedUser.setRole(newUser.getRole());
        System.out.println(updatedUser);
    }

    @DeleteMapping("{id}")
    private void DeleteUser(@PathVariable long id){
        System.out.println("deleting post with the id of: " + id);
    }
}
