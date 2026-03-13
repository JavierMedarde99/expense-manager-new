package com.expense.ui.component;

import java.time.LocalDate;

import com.expense.enums.TypesExpenses;
import com.expense.service.BillService;
import com.expense.service.SubTypesService;
import com.expense.ui.component.tables.TableBill;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;

public class MenuMain extends VerticalLayout{

    private Div mainContent;

    public MenuMain(String userName, BillService billService,SubTypesService subTypesService) {

        mainContent = new Div();
        mainContent.setSizeFull();

        TabSheet tab = new TabSheet();
        tab.add("total expenses", showTables(userName, billService,subTypesService,TypesExpenses.ALL));
        tab.add("fixed expenses", showTables(userName, billService,subTypesService,TypesExpenses.FIXED));
        tab.add("variable expenses", showTables(userName, billService,subTypesService,TypesExpenses.VARIABLE));
        mainContent.add(tab);
        
        add(mainContent);
    }


    private Div showTables(String userName, BillService billService,SubTypesService subTypesService,TypesExpenses type) {
        mainContent.removeAll();
        Div content = new Div();

        VerticalLayout thisMonth = new VerticalLayout();
        Text titleThisMonth = new Text("This Month's Expenses");
        Text totalThisMonth = new Text("The total spent this month is " +billService.getTotalByMothAndYear(LocalDate.now().getMonthValue(), LocalDate.now().getYear(), userName,type).toString());
        TableBill tableBillThisMonth = new TableBill(userName, billService,
                LocalDate.now().getMonthValue(), LocalDate.now().getYear(),subTypesService,type);
        thisMonth.add(titleThisMonth, tableBillThisMonth,totalThisMonth);

        VerticalLayout lastMonth = new VerticalLayout();
        Text titleLastMonth = new Text("Last Month's Expenses");
        Text totalLastMonth = new Text("The total spent this month is " +billService.getTotalByMothAndYear(LocalDate.now().minusMonths(1).getMonthValue(), LocalDate.now().minusMonths(1).getYear(), userName,type).toString());
        TableBill tableBillLastMonth = new TableBill(userName, billService,
                LocalDate.now().minusMonths(1).getMonthValue(), LocalDate.now().minusMonths(1).getYear(),subTypesService,type);
        lastMonth.add(titleLastMonth, tableBillLastMonth,totalLastMonth);

        content.add(thisMonth, lastMonth);
        return content;
    }
}
