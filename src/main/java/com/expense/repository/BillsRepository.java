package com.expense.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.expense.entity.Bills;
import com.expense.model.ChartDataBaseDto;

public interface BillsRepository extends CrudRepository<Bills, Long> {

    @Query(value = "SELECT * FROM bills WHERE ((EXTRACT(MONTH FROM date_bills) = :month AND EXTRACT(YEAR FROM date_bills) = :year) OR (type = 'fixed' AND EXTRACT(MONTH FROM date_bills) <= :month AND EXTRACT(YEAR FROM date_bills) <= :year AND (unsubscription_date IS NULL OR (EXTRACT(MONTH FROM unsubscription_date) > :month AND EXTRACT(YEAR FROM unsubscription_date) > :year)))) AND user_id = :user;", nativeQuery=true)
    List<Bills> getOneMonthBills(Integer month, Integer year, Long user);

    @Query(value = "select EXTRACT(YEAR FROM b.date_bills)  from bills b where b.user_id = :id order by b.date_bills LIMIT 1", nativeQuery = true)
    Integer getLastYear(Long id);

    @Query(value = "SELECT price,amount FROM bills WHERE ((EXTRACT(MONTH FROM date_bills) = :month AND EXTRACT(YEAR FROM date_bills) = :year) OR (type = 'fixed' AND EXTRACT(MONTH FROM date_bills) <= :month AND EXTRACT(YEAR FROM date_bills) <= :year AND (unsubscription_date IS NULL OR (EXTRACT(MONTH FROM unsubscription_date) > :month AND EXTRACT(YEAR FROM unsubscription_date) > :year)))) AND user_id = :user and sub_type = :subtype;", nativeQuery = true)
    List<Map<String,Number>> getChartDataByCategory(Integer month, Integer year, Long user, String subtype);

    @Query(value = "SELECT sub_type FROM bills WHERE ((EXTRACT(MONTH FROM date_bills) = :month AND EXTRACT(YEAR FROM date_bills) = :year) OR (type = 'fixed' AND EXTRACT(MONTH FROM date_bills) <= :month AND EXTRACT(YEAR FROM date_bills) <= :year AND (unsubscription_date IS NULL OR (EXTRACT(MONTH FROM unsubscription_date) > :month AND EXTRACT(YEAR FROM unsubscription_date) > :year)))) AND user_id = :user group by sub_type;", nativeQuery = true)
    List<String> getSubTypes(Integer month, Integer year, Long user);
} 

