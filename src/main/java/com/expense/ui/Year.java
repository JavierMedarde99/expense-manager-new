package com.expense.ui;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expense.service.BillService;
import com.expense.ui.component.HeaderMain;
import com.expense.ui.component.tables.TableYear;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("Year")
@RolesAllowed("USER")
@Route("year")
public class Year extends Div{
    
    public Year(BillService billService) {
        HeaderMain headerMain = new HeaderMain();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TableYear tableYear = new TableYear(billService, authentication.getName());
        add(headerMain, tableYear);
        
    }
}
