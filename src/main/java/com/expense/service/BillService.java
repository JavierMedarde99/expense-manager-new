package com.expense.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import com.expense.entity.Bills;
import com.expense.entity.Users;
import com.expense.model.BillDto;
import com.expense.repository.BillsRepository;
import com.expense.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillService {
    
    private final BillsRepository billsRepository;
    private final UsersRepository usersRepository;

    public void saveBill(BillDto billDto, String username) {
        
        Users user = getUserByUsername(username);
        Bills bills = new Bills(billDto, user);
        billsRepository.save(bills);

    }

    public List<BillDto> getBillsByUser(String username) {
        Users user = getUserByUsername(username);
        return billsRepository.getOneMonthBills(LocalDate.now().getMonthValue(), LocalDate.now().getYear(), user.getId())
                .stream()
                .map(e -> new BillDto(e.getId(),e.getAmount(), e.getName(), e.getPrice(), e.getType(), e.getSubType(), e.getDateBills()))
                .toList();
    }

    public void deleteBill(Long id) {
        billsRepository.deleteById(id);
    }

    private Users getUserByUsername(String username) {
        return usersRepository.findByUserName(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
