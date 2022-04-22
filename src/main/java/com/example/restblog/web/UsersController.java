package com.example.restblog.web;

import com.example.restblog.data.User;
import com.example.restblog.data.UsersRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        return usersRepository.findAll();
    }

    @GetMapping("{id}")
    private Optional<User> getById(@PathVariable long id){
        return usersRepository.findById(id);
    }

    @GetMapping("username")
    private User getByUsername(@RequestParam(name = "username") String username) {
        return usersRepository.findByUsername(username);
    }

    @GetMapping("email")
    private User getByEmail(@RequestParam(name = "email") String email) {
        return usersRepository.findByEmail(email);
    }

    @PostMapping
    private void createUser(@RequestBody User newUser) {
        User userToAdd = new User(0, newUser.getUsername(), newUser.getEmail(), newUser.getPassword(), LocalDate.now(), User.Role.USER, null);
        usersRepository.save(userToAdd);
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

//    @PutMapping("{id}/updatepassword")
//    private void updatePassword(   @PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @RequestParam String newPassword) {
//        User oldBoi = usersRepository.findById(id);
//        System.out.println(oldBoi);
//    }

    @DeleteMapping("{id}")
    private void DeleteUser(@PathVariable long id){
        System.out.println("deleting post with the id of: " + id);
    }
}
