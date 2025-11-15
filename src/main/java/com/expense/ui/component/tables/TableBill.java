package com.expense.ui.component.tables;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.expense.model.BillDto;
import com.expense.service.BillService;
import com.expense.ui.component.Dailogs.DailogUpdate;
import com.expense.ui.component.Dailogs.DialogDelete;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.spring.annotation.UIScope;

import com.vaadin.flow.data.renderer.ComponentRenderer;

@Component
@UIScope
public class TableBill extends Grid<BillDto> {

    public TableBill(String username, BillService billService, Integer month, Integer year) {
        
        boolean isThisMoth = month.equals(LocalDate.now().getMonthValue()) && year.equals(LocalDate.now().getYear());

        addColumn(createToggleDetailsRenderer(this))
                .setWidth("80px")
                .setFlexGrow(0)
                .setFrozen(true);

        addColumn(BillDto::getName).setHeader("Name").setSortable(true);
        addColumn(BillDto::getAmount).setHeader("Amount").setSortable(true);
        addColumn(BillDto::getPrice).setHeader("Price").setSortable(true);
        
        List<BillDto> bills = billService.getBillByMonthAndYear(username,month,year);
        setItems(bills);


        setEmptyStateText(isThisMoth ? "not bill this moth": "not bill last moth");

        setDetailsVisibleOnClick(false);

        setItemDetailsRenderer(createDetailsRenderer());

        addComponentColumn(bill -> {
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
                DialogDelete dialogDelete = new DialogDelete(billService, bill.getId(), username, month, year, this);
                dialogDelete.open();

            });

            // Layout horizontal que los contiene
            return new HorizontalLayout(editIcon, deleteIcon);
        }).setHeader("Delete/update");

        setHeight("250px");      
        getStyle().set("overflow-y", "auto"); 
        setAllRowsVisible(false); 
        setWidthFull(); 
    }

    public void reload(String username,BillService billService,Integer month, Integer year) {
        List<BillDto> bills = billService.getBillByMonthAndYear(username,month,year);
        setItems(bills); 
    }

        private static Renderer<BillDto> createToggleDetailsRenderer(Grid<BillDto> grid) {

        return LitRenderer.<BillDto>of("""
            <vaadin-button
                theme="tertiary icon"
                aria-label="Toggle details"
                aria-expanded="${model.detailsOpened ? 'true' : 'false'}"
                @click="${handleClick}"
            >
                <vaadin-icon
                    .icon="${model.detailsOpened ? 'lumo:angle-down' : 'lumo:angle-right'}"
                ></vaadin-icon>
            </vaadin-button>
        """)
        .withProperty("detailsOpened", p -> grid.isDetailsVisible(p))
        .withFunction("handleClick", bill ->
                grid.setDetailsVisible(bill, !grid.isDetailsVisible(bill))
        );
    }

    private static Renderer<BillDto> createDetailsRenderer() {
        return new ComponentRenderer<>(
            bill -> {

                TextField type = new TextField("Type", bill.getType());
                type.setReadOnly(true);
                type.setValue(bill.getType());
                TextField subType = new TextField("Subtype", bill.getSubType());
                subType.setReadOnly(true);
                subType.setValue(bill.getSubType());
                TextField dateBills = new TextField("Date", bill.getDateBills().toString());
                dateBills.setReadOnly(true);
                dateBills.setValue(bill.getDateBills().toString());
                FormLayout layout = new FormLayout();
                layout.setResponsiveSteps(
                    new ResponsiveStep("0", 1),
                    new ResponsiveStep("500px", 2)
                );

                layout.add(type, subType, dateBills);

                return layout;
            }
        );
    }
}
