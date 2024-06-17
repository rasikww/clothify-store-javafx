package edu.icet.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String name;
    private String phoneNumber;
    private String email;
    private String passwordHash;
    private Boolean isAdmin;
}
