package com.example.restblog.web;

import com.example.restblog.data.User;
import com.example.restblog.data.UsersRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/users", headers = "Accept=application/json")
public class UsersController {
    private UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping
    private List<User> getAll(){
        ArrayList<User> dummies = new ArrayList<>();

        return usersRepository.findAll();
    }

    @GetMapping("{id}")
    private User getById(@PathVariable long id){
        return usersRepository.getById(id);
    }

    @GetMapping("username")
    private User getByUsername(@RequestParam(name = "username") String username) {
        return new User(0, username, "UwU@gmail.com", "123", LocalDate.now(), User.Role.ADMIN);
    }

    @GetMapping("email")
    private User getByEmail(@RequestParam(name = "email") String email) {
        return new User(0, "BlackFidelis", email, "123", LocalDate.now(), User.Role.ADMIN);
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
        updatedUser.setCreatedAt(newUser.getCreatedAt());
        updatedUser.setRole(newUser.getRole());
        System.out.println(updatedUser);
    }

    @PutMapping("{id}/updatepassword")
    private void updatePassword(   @PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @RequestParam String newPassword) {
        User oldBoi = new User(id, "BlackFidelis", "Cracked@gmail.com", newPassword, LocalDate.now(), User.Role.ADMIN);
        System.out.println("old password was: " + oldPassword);
        System.out.println("New password is: " + newPassword);
        System.out.println(oldBoi);
    }

    @DeleteMapping("{id}")
    private void DeleteUser(@PathVariable long id){
        System.out.println("deleting post with the id of: " + id);
    }
}
