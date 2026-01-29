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
        TableBill tableBillThisMonth = new TableBill(userName, billService,
                LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        thisMonth.add(titleThisMonth, tableBillThisMonth);

        VerticalLayout lastMonth = new VerticalLayout();
        Text titleLastMonth = new Text("Last Month's Expenses");
        TableBill tableBillLastMonth = new TableBill(userName, billService,
                LocalDate.now().minusMonths(1).getMonthValue(), LocalDate.now().minusMonths(1).getYear());
        lastMonth.add(titleLastMonth, tableBillLastMonth);

        mainContent.add(thisMonth, lastMonth);
    }
}
