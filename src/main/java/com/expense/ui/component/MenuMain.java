package com.expense.ui.component;

import java.time.LocalDate;

import com.expense.service.BillService;
import com.expense.ui.component.tables.TableBill;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MenuMain extends VerticalLayout{

    private Div mainContent;

    public MenuMain(String userName, BillService billService) {

        mainContent = new Div();
        mainContent.setSizeFull();

        showTables(userName, billService);

        
        add(mainContent);
    }


    private void showTables(String userName, BillService billService) {
        mainContent.removeAll(); // Limpiamos contenido anterior

        VerticalLayout thisMonth = new VerticalLayout();
        Text titleThisMonth = new Text("This Month's Expenses");
        Text totalThisMonth = new Text("The total spent this month is " +billService.getTotalByMothAndYear(LocalDate.now().getMonthValue(), LocalDate.now().getYear(), userName).toString());
        TableBill tableBillThisMonth = new TableBill(userName, billService,
                LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        thisMonth.add(titleThisMonth, tableBillThisMonth,totalThisMonth);

        VerticalLayout lastMonth = new VerticalLayout();
        Text titleLastMonth = new Text("Last Month's Expenses");
        Text totalLastMonth = new Text("The total spent this month is " +billService.getTotalByMothAndYear(LocalDate.now().minusMonths(1).getMonthValue(), LocalDate.now().minusMonths(1).getYear(), userName).toString());
        TableBill tableBillLastMonth = new TableBill(userName, billService,
                LocalDate.now().minusMonths(1).getMonthValue(), LocalDate.now().minusMonths(1).getYear());
        lastMonth.add(titleLastMonth, tableBillLastMonth,totalLastMonth);

        mainContent.add(thisMonth, lastMonth);
    }
}
