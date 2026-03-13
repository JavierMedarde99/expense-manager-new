package com.expense.ui.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.expense.enums.TypesExpenses;
import com.expense.service.BillService;
import com.expense.service.SubTypesService;
import com.expense.ui.component.tables.TableBill;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.TabSheet;

public class FuntionsTables {
    public static Div showTable(SubTypesService subTypesService, BillService billService, String userName,
            Integer month, Integer year, TypesExpenses typesExpenses) {
        TableBill tableBill = new TableBill(userName, billService, month, year, subTypesService, typesExpenses);
        Text monthTotal = new Text("the total is " + billService.getTotalByMothAndYear(month,
                year, userName, typesExpenses).toString());
        return new Div(tableBill, monthTotal);
    }

    public static void reloadAllTables(BillService billService, SubTypesService subTypesService, Integer month,
            Integer year, String username, TabSheet tablesSheet) {

        for (int i = TypesExpenses.values().length-1; i >=0; i--) {
                tablesSheet.remove(i);
        }

        tablesSheet.add("total expenses",
                showTable(subTypesService, billService, username, month, year, TypesExpenses.ALL));
        tablesSheet.add("fixed expenses",
                showTable(subTypesService, billService, username, month, year, TypesExpenses.FIXED));
        tablesSheet.add("variable expenses",
                showTable(subTypesService, billService, username, month, year, TypesExpenses.VARIABLE));

    }
}
