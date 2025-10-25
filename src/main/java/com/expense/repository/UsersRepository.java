package com.expense.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.expense.entity.Users;

public interface UsersRepository extends CrudRepository<Users,Long>{
    Optional<Users> findByUserName(String userName);
}
