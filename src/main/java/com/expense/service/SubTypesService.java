package com.expense.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.expense.entity.Subtypes;
import com.expense.entity.Users;
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
        subTypesDtoList.forEach(subTypesDto->{
            Optional<Subtypes> optSubtype = subTypesRepository.findByIdUserAndColorAndText(usersRepository.findById(idUser).get(), subTypesDto.getColor(), subTypesDto.getName());
            if(optSubtype.isEmpty()){
                subTypesRepository.save(new Subtypes(subTypesDto, usersRepository.findById(idUser).get()));
            }
        });
    }

    public List<Subtypes> getSubTypes(String username){
        return subTypesRepository.findByIdUser(getUserByUsername(username));
    }

    public List<SubTypesDto> getAllSubtype(String username){
        List<SubTypesDto> listSubtypeDto = new ArrayList<>();
        List<Subtypes> listSubtype = subTypesRepository.findByIdUser(getUserByUsername(username));
        listSubtype.forEach(subtype->{
            listSubtypeDto.add(new SubTypesDto(subtype.getText(), subtype.getColor()));
        });
        return listSubtypeDto;
    }

    public boolean deleteSubtype(long userId, SubTypesDto subTypesDto){
        Optional<Subtypes> optSubtype = subTypesRepository.findByIdUserAndColorAndText(usersRepository.findById(userId).get(), subTypesDto.getColor(), subTypesDto.getName());
        if(optSubtype.isEmpty()){
            return true;
        }
        try {
            subTypesRepository.deleteById(optSubtype.get().getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Users getUserByUsername(String username) {
        return usersRepository.findByUserName(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
