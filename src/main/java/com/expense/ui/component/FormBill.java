package com.expense.ui.component;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.expense.model.BillDto;
import com.expense.service.BillService;
import com.expense.utils.Constants;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class FormBill extends FormLayout {

    public FormBill(String username,TableBill tableBill, BillDto billDto, BillService billService) {
        setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1), 
                new FormLayout.ResponsiveStep("1200px", 7) 
        );

        TextField billName = new TextField("name");
        if(billDto != null) {
            billName.setValue(billDto.getName());
        }

        NumberField euroField = new NumberField();
        euroField.setLabel("Balance");
        euroField.setValue(billDto == null ? 0.00: billDto.getPrice());
        Div euroSuffix = new Div();
        euroSuffix.setText("€");
        euroField.setSuffixComponent(euroSuffix);

        IntegerField amount = new IntegerField();
        amount.setStepButtonsVisible(true);
        amount.setMin(1);
        amount.setValue(billDto == null ? 1: billDto.getAmount());
        amount.setLabel("Amount");

        Select<String> selectType = new Select<>();
        selectType.setItems(Constants.TYPE);
        selectType.setLabel("Type");
        if(billDto != null) {
            selectType.setValue(billDto.getType());
        }

        Select<String> selectSubtype = new Select<>();
        selectSubtype.setItems(Constants.SUBTYPE);
        selectSubtype.setLabel("Subtype");
        if(billDto != null) {
            selectSubtype.setValue(billDto.getSubType());
        }

        DatePicker datePicker = new DatePicker("Bill date");
        datePicker.setValue(LocalDate.now());
        datePicker.setHelperText("Format: DD/MM/YYYY");
            if(billDto != null) {
                datePicker.setValue(billDto.getDateBills());
            }

        Button buttonSubmit = new Button("submit");

        buttonSubmit.addClickListener(e -> {
            BillDto billDtoNew = new BillDto(null,amount.getValue(), billName.getValue(), euroField.getValue(),
                    selectType.getValue(), selectSubtype.getValue(), datePicker.getValue());
            if(tableBill != null) {
                saveBill(billDtoNew, username,tableBill, billService);
            }else{
                updateBill(billDtoNew, username,billService);
            }
        });

        add(billName, euroField, selectType, selectSubtype, datePicker, amount, buttonSubmit);
    }

    private void saveBill(BillDto billDto, String username,TableBill tableBill, BillService billService) {
        billService.saveBill(billDto, username);
        tableBill.reload(username,billService);
    }

    private void updateBill(BillDto billDto, String username, BillService billService) {
        billService.updateBill(billDto, username);
        UI.getCurrent().getPage().reload();
    }
}
