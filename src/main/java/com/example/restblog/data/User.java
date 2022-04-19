package com.example.restblog.data;

import lombok.*;

import javax.validation.constraints.Size;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
private long id;
private String username;
private String email;
@Size(min = 3)
private String password;
private LocalDate createdAt;
private Role role;

public enum Role {USER, ADMIN}
}
