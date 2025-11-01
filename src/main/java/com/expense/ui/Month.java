package com.expense.ui;

import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expense.service.BillService;
import com.expense.ui.component.HeaderMain;
import com.expense.ui.component.TableBill;
import com.expense.ui.component.Forms.FormMonth;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("Moth")
@RolesAllowed("USER")
@Route("moth")
public class Month extends Div{
    
    public Month(BillService billService) {
        HeaderMain headerMain = new HeaderMain();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        TableBill tableBill = new TableBill(authentication.getName(), billService,LocalDate.now().getMonthValue(),LocalDate.now().getYear());
        FormMonth formMonth = new FormMonth(billService,authentication.getName(),tableBill);
        formMonth.getStyle()
        .set("margin", "20px");

        

        add(headerMain,formMonth,tableBill);
    }
}
