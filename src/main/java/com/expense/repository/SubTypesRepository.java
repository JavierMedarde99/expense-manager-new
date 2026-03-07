package com.expense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.expense.entity.Subtypes;
import com.expense.entity.Users;

public interface SubTypesRepository extends CrudRepository<Subtypes,Long>{

    List<Subtypes> findByIdUser(Users idUser);

    Optional<Subtypes> findByIdUserAndColorAndText(Users idUser,String color,String text);
}
