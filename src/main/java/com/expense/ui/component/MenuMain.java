package com.expense.ui.component;

import java.time.LocalDate;

import com.expense.service.BillService;
import com.expense.ui.component.tables.TableBill;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MenuMain extends VerticalLayout{

    private Div mainContent;

    public MenuMain(String userName, BillService billService) {
        MenuBar menuBar = new MenuBar();
        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        MenuItem stats = menuBar.addItem("starts");
        MenuItem data = menuBar.addItem("data");

        mainContent = new Div();
        mainContent.setSizeFull();

        // Acción al hacer click en stats
        stats.addClickListener(e -> showStats(userName, billService));

        // Acción al hacer click en data (mostrar tablas)
        data.addClickListener(e -> showTables(userName, billService));

        // Inicialmente mostramos las tablas
        showStats(userName, billService);
        
        add(menuBar,mainContent);
    }


    private void showTables(String userName, BillService billService) {
        mainContent.removeAll(); // Limpiamos contenido anterior

        VerticalLayout thisMonth = new VerticalLayout();
        Text titleThisMonth = new Text("This Month's Expenses");
        TableBill tableBillThisMonth = new TableBill(userName, billService,
                LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        thisMonth.add(titleThisMonth, tableBillThisMonth);

        VerticalLayout lastMonth = new VerticalLayout();
        Text titleLastMonth = new Text("Last Month's Expenses");
        TableBill tableBillLastMonth = new TableBill(userName, billService,
                LocalDate.now().minusMonths(1).getMonthValue(), LocalDate.now().minusMonths(1).getYear());
        lastMonth.add(titleLastMonth, tableBillLastMonth);

        mainContent.add(thisMonth, lastMonth);
    }

    private void showStats(String userName, BillService billService) {
        mainContent.removeAll(); // Limpiamos contenido anterior

        HorizontalLayout chartsLayout = new HorizontalLayout();
        chartsLayout.setWidthFull();
        chartsLayout.getStyle().set("display", "flex");
        chartsLayout.getStyle().set("flex-wrap", "wrap");
        chartsLayout.getStyle().set("justify-content", "space-around");
        
        Div pieChartThisMoth = new ChartPie(billService, userName, LocalDate.now().getMonthValue(), LocalDate.now().getYear(), "This Month");
        pieChartThisMoth.setWidth("500px");
        Div pieChartLastMoth = new ChartPie(billService, userName, LocalDate.now().minusMonths(1).getMonthValue(), LocalDate.now().minusMonths(1).getYear(), "Last Month");
        pieChartLastMoth.setWidth("500px");

        chartsLayout.add(pieChartThisMoth, pieChartLastMoth);
        mainContent.add(chartsLayout);
    }
}
