package com.expense.model;

import java.time.LocalDate;

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
    String subType; 
    LocalDate dateBills;
}
