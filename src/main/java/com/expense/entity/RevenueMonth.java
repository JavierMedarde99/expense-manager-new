package com.expense.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class RevenueMonth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer month;
    private Integer year;
    private Double revenue;
    private Double total;
    private Double monthSalary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users userId;

    public RevenueMonth(Integer month,Integer year,Double revenue,Users idUser,Double total,Double monthSalary){
        this.month = month;
        this.year = year;
        this.revenue = revenue;
        this.userId = idUser;
        this.total = total;
        this.monthSalary = monthSalary;
    }
}
