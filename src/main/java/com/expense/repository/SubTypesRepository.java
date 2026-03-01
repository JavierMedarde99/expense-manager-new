package com.expense.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.expense.entity.Subtypes;
import com.expense.entity.Users;

public interface SubTypesRepository extends CrudRepository<Subtypes,Long>{

    List<Subtypes> findByIdUser(Users idUser);
}
