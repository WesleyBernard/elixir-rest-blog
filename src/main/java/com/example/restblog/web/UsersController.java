package com.example.restblog.web;

import com.example.restblog.data.User;
import com.example.restblog.data.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/users", headers = "Accept=application/json")
public class UsersController {
    private  final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    @GetMapping
    public List<User> getAll(){
        return usersRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<User> getById(@PathVariable long id){
        return usersRepository.findById(id);
    }

    @GetMapping("username")
    public User getByUsername(@RequestParam(name = "username") String username) {
        return usersRepository.findByUsername(username);
    }

    @GetMapping("email")
    public User getByEmail(@RequestParam(name = "email") String email) {
        return usersRepository.findByEmail(email);
    }

    @PostMapping
    public void createUser(@RequestBody User newUser) {
        newUser.setRole(User.Role.USER);
        newUser.setCreatedAt(LocalDate.now());
        String encryptedPassword = newUser.getPassword();
        encryptedPassword = passwordEncoder.encode(encryptedPassword);
        newUser.setPassword(encryptedPassword);
        usersRepository.save(newUser);
        System.out.println("Adding user...." + newUser);
    }

    @GetMapping("me")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public User getCurrent(OAuth2Authentication auth) {
        return usersRepository.findByEmail(auth.getName());
    }

    @PutMapping("{id}")
    public void updateUser(@RequestBody User newUser, @PathVariable long id) {
        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setUsername(newUser.getUsername());
        updatedUser.setEmail(newUser.getEmail());
        updatedUser.setPassword(newUser.getPassword());
        updatedUser.setCreatedAt(newUser.getCreatedAt());
        updatedUser.setRole(newUser.getRole());
        System.out.println(updatedUser);
    }

    @PutMapping("updatepassword")
    private void updatePassword(@RequestParam(required = false) String oldPassword, @Valid @RequestParam String newPassword, OAuth2Authentication auth) {
        System.out.println(auth.getName());
        User user = usersRepository.findByEmail(auth.getName());
        if (passwordEncoder.encode(user.getPassword()).equals(oldPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));
        } else {
            System.out.println("Old password didn't match password for the current user");
        }

    }

    @DeleteMapping("{id}")
    public void DeleteUser(@PathVariable long id){
        System.out.println("deleting post with the id of: " + id);
    }
}
