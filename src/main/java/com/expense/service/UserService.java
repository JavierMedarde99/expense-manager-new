package com.expense.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expense.entity.Users;
import com.expense.model.UserDto;
import com.expense.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean newUser(UserDto userDto) {
        var user = usersRepository.findByUserName(userDto.getUserName());
        if(user.isPresent()) {
            return false;
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        var newUser = new Users(userDto);
        usersRepository.save(newUser);
        return true;
    }

    public UserDto getUserByUsername(String username) {
        Users user = usersRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDto(user);
    }

    public void deleteUser(String username) {
        Users user = usersRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        usersRepository.delete(user);
    }

    public void changePassword(String username, String newPassword) {
        Users user = usersRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        usersRepository.save(user);
    }

    public void updateUser(String username, UserDto userDto) {
        Users user = usersRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        user.setSalary(userDto.getSalary());
        usersRepository.save(user);
    }
}
