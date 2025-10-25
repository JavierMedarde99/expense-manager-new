package com.expense.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expense.ui.component.FormBill;
import com.expense.ui.component.TableBill;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@RolesAllowed("USER")
@Route("")
public class Main extends Div {

    private final FormBill formBill;
    private final TableBill tableBill;

    @Autowired 
    public Main(FormBill formBill, TableBill tableBill) {
        this.formBill = formBill;
        this.tableBill = tableBill;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        formBill.init(authentication.getName(),tableBill);

        tableBill.init(authentication.getName());
        add(formBill,tableBill);
    }
}
