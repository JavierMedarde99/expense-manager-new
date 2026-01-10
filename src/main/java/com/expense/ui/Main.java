package com.expense.ui;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expense.service.BillService;
import com.expense.ui.component.FooterPage;
import com.expense.ui.component.HeaderMain;
import com.expense.ui.component.MenuMain;
import com.expense.ui.component.Forms.FormBill;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("Dashboard")
@RolesAllowed("USER")
@Route(value = "", layout = HeaderMain.class)
public class Main extends VerticalLayout {

    public Main(BillService billService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        FormBill formBill = new FormBill(authentication.getName(), null, null, billService);
        formBill.getStyle()
                .set("margin-left", "20px")
                .set("margin-right", "20px");

        MenuMain menuMain = new MenuMain(authentication.getName(), billService);

        FooterPage footer = new FooterPage();

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        add(formBill, menuMain, menuMain, footer);
        expand(formBill, menuMain);
    }
}

