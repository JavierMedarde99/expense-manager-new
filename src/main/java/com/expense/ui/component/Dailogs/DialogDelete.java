package com.expense.ui.component.Dailogs;

import com.expense.service.BillService;
import com.expense.ui.component.TableBill;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

public class DialogDelete extends Dialog{
    
    public DialogDelete(BillService billService, Long billId,String username,Integer month, Integer year, TableBill tableBill) {
        setHeaderTitle("Delete Expense");

        Text text = new Text("Are you sure you want to delete this expense?");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull(); // Para que ocupe todo el ancho del diálogo
        layout.setAlignItems(Alignment.CENTER);

        Button btnConfirm = new Button("Confirm");
        btnConfirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
        ButtonVariant.LUMO_ERROR);
        btnConfirm.addClickListener(e ->{
            billService.deleteBill(billId);
            tableBill.reload(username, billService, month, year);
            close();
        });

        Button btnCancel = new Button("Cancel", e -> close());

        layout.add(btnConfirm,btnCancel);

        add(text, layout);
    }
}
