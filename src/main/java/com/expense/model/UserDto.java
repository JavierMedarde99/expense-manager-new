package com.expense.model;

import com.expense.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    private String userName;
    private String email;
    private String password;
    private Double salary;

    public UserDto(Users user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.salary = user.getSalary();
    }
}
