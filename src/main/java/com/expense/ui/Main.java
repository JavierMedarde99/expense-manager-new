package com.expense.ui;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expense.service.BillService;
import com.expense.ui.component.FormBill;
import com.expense.ui.component.TableBill;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@RolesAllowed("USER")
@Route("")
public class Main extends Div {

    public Main(BillService billService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        TableBill tableBill = new TableBill(authentication.getName(), billService);
        FormBill formBill = new FormBill(authentication.getName(),tableBill,null,billService);

        
        add(formBill,tableBill);
    }
}
