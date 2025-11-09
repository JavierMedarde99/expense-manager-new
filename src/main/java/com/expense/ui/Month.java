package com.expense.ui;

import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expense.service.BillService;
import com.expense.ui.component.FooterPage;
import com.expense.ui.component.HeaderMain;
import com.expense.ui.component.Forms.FormMonth;
import com.expense.ui.component.tables.TableBill;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("Moth")
@RolesAllowed("USER")
@Route("moth")
public class Month extends VerticalLayout{
    
    public Month(BillService billService) {
        setSizeFull(); // ocupa toda la pantalla
        setPadding(false);
        setSpacing(false);

        HeaderMain headerMain = new HeaderMain();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        TableBill tableBill = new TableBill(authentication.getName(), billService,LocalDate.now().getMonthValue(),LocalDate.now().getYear());
        FormMonth formMonth = new FormMonth(billService,authentication.getName(),tableBill);
        formMonth.getStyle()
        .set("margin", "20px");

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setAlignItems(Alignment.CENTER);

        layout.add(formMonth,tableBill);

        FooterPage footer = new FooterPage();

        add(headerMain,layout,footer);
    }
}
