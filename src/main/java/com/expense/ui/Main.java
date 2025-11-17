package com.expense.ui;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expense.service.BillService;
import com.expense.ui.component.FooterPage;
import com.expense.ui.component.HeaderMain;
import com.expense.ui.component.MenuMain;
import com.expense.ui.component.Forms.FormBill;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("Dashboard")
@RolesAllowed("USER")
@Route("")
public class Main extends Div {

    public Main(BillService billService) {
        HeaderMain headerMain = new HeaderMain();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        FormBill formBill = new FormBill(authentication.getName(),null,null,billService);
        formBill.getStyle()
        .set("margin-left", "20px")
        .set("margin-right", "20px");

        MenuMain menuMain = new MenuMain(authentication.getName(),billService);


        
        FooterPage footer = new FooterPage();
        add(headerMain, formBill,menuMain,menuMain,footer);
    }
}
