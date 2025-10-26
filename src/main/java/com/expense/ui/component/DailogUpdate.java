package com.expense.ui.component;

import org.springframework.stereotype.Component;

import com.expense.model.BillDto;
import com.expense.service.BillService;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.spring.annotation.UIScope;


@Component
@UIScope
public class DailogUpdate extends Dialog{

    public DailogUpdate(String username,BillService billService,Long billId) {
        FormBill formBill = new FormBill(username,null,getBillById(billService, billId),billService);
        setHeaderTitle("Update Expense");
        add(formBill);
    }

    private BillDto getBillById(BillService billService, Long billId) {
        return billService.getBillById(billId);
    }
}
