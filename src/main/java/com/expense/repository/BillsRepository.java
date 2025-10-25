package com.expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.expense.entity.Bills;

public interface BillsRepository extends CrudRepository<Bills, Long> {

    @Query(value = "SELECT * FROM bills WHERE ((EXTRACT(MONTH FROM date_bills) = :month AND EXTRACT(YEAR FROM date_bills) = :year) OR (type = 'fixed' AND EXTRACT(MONTH FROM date_bills) <= :month AND EXTRACT(YEAR FROM date_bills) <= :year AND (unsubscription_date IS NULL OR (EXTRACT(MONTH FROM unsubscription_date) > :month AND EXTRACT(YEAR FROM unsubscription_date) > :year)))) AND user_id = :user;", nativeQuery=true)
    List<Bills> getOneMonthBills(Integer month, Integer year, Long user);

    @Query(value = "select EXTRACT(YEAR FROM b.date_bills)  from bills b where b.user_id = :id order by b.date_bills LIMIT 1", nativeQuery = true)
    Integer getLastYear(Long id);
} 
