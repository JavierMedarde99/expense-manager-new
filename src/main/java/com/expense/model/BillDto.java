package com.expense.model;

import java.time.LocalDate;

import com.expense.entity.Bills;
import com.expense.entity.Subtypes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillDto {
    Long id;
    Integer amount;
    String name;
    Double price;
    String type;
    Subtypes subType;
    LocalDate dateBills;

    public BillDto(Bills bills) {
        this.id = bills.getId();
        this.amount = bills.getAmount();
        this.name = bills.getName();
        this.price = bills.getPrice();
        this.type = bills.getType();
        this.subType = bills.getIdSubtype();
        this.dateBills = bills.getDateBills();
    }

    public String getRowColor() {
        return subType != null ? subType.getColor() : null;
    }
}
