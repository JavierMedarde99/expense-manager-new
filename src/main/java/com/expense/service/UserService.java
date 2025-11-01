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
}
