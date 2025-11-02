package com.expense.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.expense.entity.Bills;
import com.expense.entity.Users;
import com.expense.model.BillDto;
import com.expense.repository.BillsRepository;
import com.expense.repository.RevenueMonthRepository;
import com.expense.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillService {
    
    private final BillsRepository billsRepository;
    private final UsersRepository usersRepository;
    private final RevenueMonthRepository revenueMonthRepository;

    public void saveBill(BillDto billDto, String username) {
        
        Users user = getUserByUsername(username);
        Bills bills = new Bills(billDto, user);
        billsRepository.save(bills);

    }

    public List<BillDto> getBillByMonthAndYear(String username, int month, int year) {
        Users user = getUserByUsername(username);
        return billsRepository.getOneMonthBills(month, year, user.getId())
                .stream()
                .map(BillDto::new)
                .toList();

    }

    public void deleteBill(Long id) {
        billsRepository.deleteById(id);
    }

    public BillDto getBillById(Long billId) {
        Bills bills = billsRepository.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found"));
        return new BillDto(bills);
    }

    public void updateBill(BillDto billDto, String username) {
        Users user = getUserByUsername(username);
        Bills bills = new Bills(billDto, user);
        bills.setId(billDto.getId());
        billsRepository.save(bills);
    }

    public List<Map<String, Object>> getAllBillsYear(String username) {
        Users user = getUserByUsername(username);
        return revenueMonthRepository.geyAllYear(user.getId());
    }

    private Users getUserByUsername(String username) {
        return usersRepository.findByUserName(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
