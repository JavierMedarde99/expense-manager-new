package com.expense.ui.component;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.charts.model.Configuration;

public class ChartsBill extends VerticalLayout{
    public ChartsBill(String title) {
        Chart chart = new Chart(ChartType.PIE);
        Configuration conf = chart.getConfiguration();
        conf.setTitle(title);

        
        add(chart);
        
    }

    
}
