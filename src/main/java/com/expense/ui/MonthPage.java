package com.expense.ui;

import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expense.enums.TypesExpenses;
import com.expense.service.BillService;
import com.expense.service.SubTypesService;
import com.expense.ui.component.FooterPage;
import com.expense.ui.component.HeaderMain;
import com.expense.ui.component.Forms.FormMonth;
import com.expense.ui.component.tables.TableBill;
import com.expense.ui.utils.FuntionsTables;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("Moth")
@RolesAllowed("USER")
@Route(value = "moth", layout = HeaderMain.class)
public class MonthPage extends VerticalLayout{
    
    public MonthPage(BillService billService,SubTypesService subTypesService) {
        setSizeFull(); // ocupa toda la pantalla
        setPadding(false);
        setSpacing(false);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        TabSheet tab = new TabSheet();
        tab.add("total expenses", FuntionsTables.showTable(subTypesService, billService, authentication.getName(), LocalDate.now().getMonthValue(),LocalDate.now().getYear(), TypesExpenses.ALL));
        tab.add("fixed expenses", FuntionsTables.showTable(subTypesService, billService, authentication.getName(), LocalDate.now().getMonthValue(),LocalDate.now().getYear(), TypesExpenses.FIXED));
        tab.add("variable expenses", FuntionsTables.showTable(subTypesService, billService, authentication.getName(), LocalDate.now().getMonthValue(),LocalDate.now().getYear(), TypesExpenses.VARIABLE));
        tab.setSizeFull();
        
        FormMonth formMonth = new FormMonth(billService,subTypesService,authentication.getName(),tab);
        formMonth.getStyle()
        .set("margin", "20px");

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setAlignItems(Alignment.CENTER);

        layout.add(formMonth,tab);

        FooterPage footer = new FooterPage();

        add(layout, footer);
    }


}
