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
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.DateRangeValidator;
import com.vaadin.flow.data.validator.DoubleRangeValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class FormBill extends FormLayout {

    public FormBill(String username, TableBill tableBill, BillDto billDto, BillService billService) {

        if (billDto == null) {
            setResponsiveSteps(
                    new FormLayout.ResponsiveStep("0", 1),
                    new FormLayout.ResponsiveStep("1000px", 7));
        } else {
            setResponsiveSteps(
                    new FormLayout.ResponsiveStep("0", 1),
                    new FormLayout.ResponsiveStep("1000px", 2));
        }

        TextField billName = new TextField("name");
        billName.setRequiredIndicatorVisible(true);
        billName.setErrorMessage("name is required");
        if (billDto != null) {
            billName.setValue(billDto.getName());
        }

        NumberField euroField = new NumberField();
        euroField.setLabel("Balance");
        euroField.setValue(billDto == null ? 0.00 : billDto.getPrice());
        Div euroSuffix = new Div();
        euroSuffix.setText("€");
        euroField.setSuffixComponent(euroSuffix);
        euroField.setRequiredIndicatorVisible(true);
        euroField.setErrorMessage("balance is required");

        IntegerField amount = new IntegerField();
        amount.setStepButtonsVisible(true);
        amount.setMin(1);
        amount.setValue(billDto == null ? 1 : billDto.getAmount());
        amount.setLabel("Amount");
        amount.setRequiredIndicatorVisible(true);
        amount.setErrorMessage("amount is required");

        Select<String> selectType = new Select<>();
        selectType.setItems(Constants.TYPE);
        selectType.setLabel("Type");
        if (billDto != null) {
            selectType.setValue(billDto.getType());
        }
        selectType.setRequiredIndicatorVisible(true);
        selectType.setErrorMessage("type is required");

        Select<String> selectSubtype = new Select<>();
        selectSubtype.setItems(Constants.SUBTYPE);
        selectSubtype.setLabel("Subtype");
        if (billDto != null) {
            selectSubtype.setValue(billDto.getSubType());
        }
        selectSubtype.setRequiredIndicatorVisible(true);
        selectSubtype.setErrorMessage("subtype is required");

        DatePicker datePicker = new DatePicker("Bill date");
        datePicker.setValue(LocalDate.now());
        datePicker.setHelperText("Format: DD/MM/YYYY");
        if (billDto != null) {
            datePicker.setValue(billDto.getDateBills());
        }
        datePicker.setRequiredIndicatorVisible(true);
        datePicker.setErrorMessage("date is required");

        Binder<BillDto> binder = new Binder<>(BillDto.class);

        binder.forField(billName)
                .asRequired("Name is required")
                .withValidator(new StringLengthValidator("Name must be between 2 and 50 characters", 2, 50))
                .bind(BillDto::getName, BillDto::setName);

        binder.forField(euroField)
                .asRequired("Balance is required")
                .withValidator(new DoubleRangeValidator("Balance must be greater than 0", 0.01, null))
                .bind(BillDto::getPrice, BillDto::setPrice);

        binder.forField(amount)
                .asRequired("Amount is required")
                .withValidator(v -> v != null && v >= 1, "Amount must be at least 1")
                .bind(BillDto::getAmount, BillDto::setAmount);

        binder.forField(selectType)
                .asRequired("Type is required")
                .bind(BillDto::getType, BillDto::setType);

        binder.forField(selectSubtype)
                .asRequired("Subtype is required")
                .bind(BillDto::getSubType, BillDto::setSubType);

        binder.forField(datePicker)
                .asRequired("Date is required")
                .withValidator(new DateRangeValidator("Date cannot be in the future", null, LocalDate.now()))
                .bind(BillDto::getDateBills, BillDto::setDateBills);

        if (billDto != null) {
            binder.readBean(billDto);
        } else {
            datePicker.setValue(LocalDate.now());
        }

        Button buttonSubmit = new Button("submit");

        buttonSubmit.addClickListener(e -> {
            if (binder.validate().isOk()) {
                BillDto billDtoNew = new BillDto(null, amount.getValue(), billName.getValue(), euroField.getValue(),
                        selectType.getValue(), selectSubtype.getValue(), datePicker.getValue());
                if (tableBill != null) {
                    saveBill(billDtoNew, username, billService);
                } else {
                    updateBill(billDtoNew, username, billService);
                }
            }
        });

        add(billName, euroField, selectType, selectSubtype, datePicker, amount, buttonSubmit);
    }

    private void saveBill(BillDto billDto, String username, BillService billService) {
        billService.saveBill(billDto, username);
        UI.getCurrent().getPage().reload();
    }

    private void updateBill(BillDto billDto, String username, BillService billService) {
        billService.updateBill(billDto, username);
        UI.getCurrent().getPage().reload();
    }
}
