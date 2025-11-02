package com.expense.ui.component.tables;

import java.util.List;
import java.util.Map;

import com.expense.service.BillService;
import com.vaadin.flow.component.grid.Grid;

public class TableYear extends Grid<Map<String, Object>> {
    
    public TableYear(BillService billService, String username) {
        addColumn(item -> item.get("year_value")).setHeader("Year");
        addColumn(item -> item.get("total_bill_year")).setHeader("Total");
        addColumn(item -> item.get("total_earnings")).setHeader("Earnings");
        addColumn(item -> item.get("money_saved")).setHeader("Money Saved");

        List<Map<String, Object>> map = billService.getAllBillsYear(username);

        setItems(map);
        
        setEmptyStateText("No data available for years");
    }
    
}
