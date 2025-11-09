package com.expense.ui;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expense.service.BillService;
import com.expense.ui.component.FooterPage;
import com.expense.ui.component.HeaderMain;
import com.expense.ui.component.tables.TableYear;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("Year")
@RolesAllowed("USER")
@Route("year")
public class Year extends VerticalLayout{
    
    public Year(BillService billService) {
        setSizeFull(); // ocupa toda la pantalla
        setPadding(false);
        setSpacing(false);
        HeaderMain headerMain = new HeaderMain();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TableYear tableYear = new TableYear(billService, authentication.getName());

                VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setAlignItems(Alignment.CENTER);

        layout.add(tableYear);
        FooterPage footer = new FooterPage();
        add(headerMain, layout,footer);
        
    }
}
