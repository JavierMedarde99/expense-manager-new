package com.expense.entity;

import com.expense.model.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(schema = "expense_manager")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;
    private String email;
    private String password;
    private Double salary;

    public Users(UserDto userDto) {
        this.userName = userDto.getUserName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.salary = userDto.getSalary();
    }

}
