package com.expense.ui;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expense.service.BillService;
import com.expense.ui.component.FormBill;
import com.expense.ui.component.HeaderMain;
import com.expense.ui.component.TableBill;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@RolesAllowed("USER")
@Route("")
public class Main extends Div {

    public Main(BillService billService) {
        HeaderMain headerMain = new HeaderMain();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        VerticalLayout thisMoth = new VerticalLayout();
        Text titleThisMoth = new Text("This Month's Expenses");
        TableBill tableBillThisMoth = new TableBill(authentication.getName(), billService,true);
        thisMoth.add(titleThisMoth,tableBillThisMoth);
        
        FormBill formBill = new FormBill(authentication.getName(),tableBillThisMoth,null,billService);
        formBill.getStyle()
        .set("margin-left", "20px")
        .set("margin-right", "20px");

        VerticalLayout lastMoth = new VerticalLayout();
        Text titleLastMoth = new Text("Last Month's Expenses");
        TableBill tableBillLastMoth = new TableBill(authentication.getName(), billService,false);
        thisMoth.add(titleLastMoth,tableBillLastMoth);
        
        add(headerMain, formBill,thisMoth, lastMoth);
    }
}
