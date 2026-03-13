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
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("Moth")
@RolesAllowed("USER")
@Route(value = "moth", layout = HeaderMain.class)
public class Month extends VerticalLayout{
    
    public Month(BillService billService,SubTypesService subTypesService) {
        setSizeFull(); // ocupa toda la pantalla
        setPadding(false);
        setSpacing(false);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Text monthTotal = new Text("the total is "+ billService.getTotalByMothAndYear(LocalDate.now().getMonthValue(),LocalDate.now().getYear(),authentication.getName()).toString());
        
        TabSheet tab = new TabSheet();
        tab.add("total expenses", new TableBill(authentication.getName(), billService,LocalDate.now().getMonthValue(),LocalDate.now().getYear(),subTypesService,TypesExpenses.ALL));
        tab.add("fixed expenses", new TableBill(authentication.getName(), billService,LocalDate.now().getMonthValue(),LocalDate.now().getYear(),subTypesService,TypesExpenses.FIXED));
        tab.add("variable expenses", new TableBill(authentication.getName(), billService,LocalDate.now().getMonthValue(),LocalDate.now().getYear(),subTypesService,TypesExpenses.VARIABLE));
        
        FormMonth formMonth = new FormMonth(billService,authentication.getName(),null,null);
        formMonth.getStyle()
        .set("margin", "20px");

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setAlignItems(Alignment.CENTER);

        layout.add(formMonth,null,tab);

        FooterPage footer = new FooterPage();

        add(layout, footer);
    }
}
