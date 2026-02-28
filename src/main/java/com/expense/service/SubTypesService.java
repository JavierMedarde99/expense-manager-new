package com.expense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expense.entity.Subtypes;
import com.expense.model.SubTypesDto;
import com.expense.repository.SubTypesRepository;
import com.expense.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubTypesService {
    
    private final SubTypesRepository subTypesRepository;
    private final UsersRepository usersRepository;

    public void saveAllSubtype(Long idUser,List<SubTypesDto> subTypesDtoList){
        subTypesDtoList.forEach(subTypesDto->subTypesRepository.save(new Subtypes(subTypesDto, usersRepository.findById(idUser).get())));
    }
}
