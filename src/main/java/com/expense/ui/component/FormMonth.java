package com.expense.ui.component;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.expense.model.BillDto;
import com.expense.model.MonthArray;
import com.expense.service.BillService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class FormMonth extends FormLayout {

    public FormMonth(BillService billService, String username, TableBill tableBill) {

        setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("1000px", 3));

        Select<MonthArray> selectMoth = new Select<>();
        selectMoth.setItems(getSelectMonth());
        selectMoth.setItemLabelGenerator(MonthArray::getName);
        String monthName = Month.of(LocalDate.now().getMonthValue()).name().substring(0, 1).toUpperCase() + Month.of(LocalDate.now().getMonthValue()).name().substring(1).toLowerCase();
        selectMoth.setValue(new MonthArray(LocalDate.now().getMonthValue(), monthName));
        selectMoth.setLabel("Month");

        Select<Integer> selectYear = new Select<>();
        selectYear.setItems(getYears());
        selectYear.setValue(LocalDate.now().getYear());
        selectYear.setLabel("Year");

        Button buttonFilter = new Button("Filter");

        buttonFilter.addClickListener(e -> {
            List<BillDto> bills = billService.getBillByMonthAndYear(username,
                    selectMoth.getValue().getValue(),
                    selectYear.getValue());
            tableBill.setItems(bills);
        });
        add(selectMoth, selectYear, buttonFilter);
    }

    private List<MonthArray> getSelectMonth() {
        List<MonthArray> monthList = new ArrayList<>();
        for (int i = 1; Month.values().length >= i; i++) {
            String monthName = Month.of(i).name().substring(0, 1).toUpperCase() + Month.of(i).name().substring(1).toLowerCase();
            monthList.add(new MonthArray(i, monthName));
        }
        return monthList;
    }

    private List<Integer> getYears() {
        List<Integer> yearList = new ArrayList<>();
        int currentYear = java.time.Year.now().getValue();
        for (int i = currentYear; i >= 2000; i--) {
            yearList.add(i);
        }
        return yearList;
    }
}
