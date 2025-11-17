package com.expense.ui.component;

import com.expense.service.BillService;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Div;

public class ChartPie extends Div {
    public ChartPie(BillService billService, String userName, int month, int year,String title) {

        Chart pieChart = new Chart(ChartType.PIE);
        Configuration conf = pieChart.getConfiguration();
        conf.setTitle(title);
        DataSeries series = new DataSeries();

        billService.chartData(userName, month, year)
            .forEach(data -> series.add(new DataSeriesItem(data.getCategory(), data.getCount())));


        conf.setSeries(series);
        add(pieChart);
    }
    
}
