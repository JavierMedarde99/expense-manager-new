package com.expense.ui.component;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.expense.model.BillDto;
import com.expense.service.BillService;
import com.expense.utils.Constants;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

@Component
public class FormBill extends FormLayout {

    private final BillService billService;

    public FormBill(BillService billService) {
        this.billService = billService;
        setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1), 
                new FormLayout.ResponsiveStep("1200px", 7) 
        );
    }

    public void init(String username, TableBill tableBill) {
        TextField billName = new TextField("name");

        NumberField euroField = new NumberField();
        euroField.setLabel("Balance");
        euroField.setValue(0.00);
        Div euroSuffix = new Div();
        euroSuffix.setText("€");
        euroField.setSuffixComponent(euroSuffix);

        IntegerField amount = new IntegerField();
        amount.setStepButtonsVisible(true);
        amount.setMin(1);
        amount.setValue(1);
        amount.setLabel("Amount");

        Select<String> selectType = new Select<>();
        selectType.setItems(Constants.TYPE);
        selectType.setLabel("Type");

        Select<String> selectSubtype = new Select<>();
        selectSubtype.setItems(Constants.SUBTYPE);
        selectSubtype.setLabel("Subtype");

        DatePicker datePicker = new DatePicker("Bill date");
        datePicker.setValue(LocalDate.now());
        datePicker.setHelperText("Format: DD/MM/YYYY");

        Button buttonSubmit = new Button("submit");

        buttonSubmit.addClickListener(e -> {
            BillDto billDto = new BillDto(null,amount.getValue(), billName.getValue(), euroField.getValue(),
                    selectType.getValue(), selectSubtype.getValue(), datePicker.getValue());
            billService.saveBill(billDto, username);
            tableBill.reload(username);
        });

        add(billName, euroField, selectType, selectSubtype, datePicker, amount, buttonSubmit);
    }

}
