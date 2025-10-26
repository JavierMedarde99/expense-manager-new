package com.expense.ui.component;

import java.util.List;

import org.springframework.stereotype.Component;

import com.expense.model.BillDto;
import com.expense.service.BillService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class TableBill extends Grid<BillDto> {

    public TableBill(String username, BillService billService, boolean isThisMoth) {
        
        this.addColumn(BillDto::getName).setHeader("Name").setSortable(true);
        this.addColumn(BillDto::getAmount).setHeader("Amount").setSortable(true);
        this.addColumn(BillDto::getPrice).setHeader("Price").setSortable(true);
        this.addColumn(BillDto::getType).setHeader("Type").setSortable(true);
        this.addColumn(BillDto::getSubType).setHeader("Subtype").setSortable(true);
        this.addColumn(BillDto::getDateBills).setHeader("Date").setSortable(true);
        

        List<BillDto> bills = billService.getBillsByUser(username,isThisMoth);
        this.setItems(bills);


        this.setEmptyStateText(isThisMoth ? "not bill this moth": "not bill last moth");

        this.addComponentColumn(bill -> {
            // Icono 1
            Icon editIcon = new Icon(VaadinIcon.EDIT);
            editIcon.getStyle().set("cursor", "pointer");
            editIcon.addClickListener(e -> {
                // Acción de eliminar
                DailogUpdate dialogUpdate = new DailogUpdate(username, billService, bill.getId());
                dialogUpdate.open();
            });

            // Icono 2
            Icon deleteIcon = new Icon(VaadinIcon.TRASH);
            deleteIcon.getStyle().set("cursor", "pointer");
            deleteIcon.addClickListener(e -> {
                billService.deleteBill(bill.getId());
                reload(username,billService,isThisMoth);

            });

            // Layout horizontal que los contiene
            return new HorizontalLayout(editIcon, deleteIcon);
        }).setHeader("Delete/update");

        this.setHeight("250px");      
        this.getStyle().set("overflow-y", "auto"); 
        this.setAllRowsVisible(false); 
        this.setWidthFull(); 
    }

    public void reload(String username,BillService billService,boolean isThisMoth) {
        List<BillDto> bills = billService.getBillsByUser(username,isThisMoth);
        this.setItems(bills); 
    }
}
