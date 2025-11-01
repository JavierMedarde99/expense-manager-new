package com.expense.ui.component;

import org.springframework.stereotype.Component;

import com.expense.ui.Main;
import com.expense.ui.Month;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Component
@UIScope
public class HeaderMain extends AppLayout{
    
    public HeaderMain() {
        // Botón hamburguesa
        DrawerToggle toggle = new DrawerToggle();

        // Logo / título
        H1 logo = new H1("Expense Manager");
        logo.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        // Header
        HorizontalLayout header = new HorizontalLayout(toggle, logo);
        header.setWidthFull();
        header.addClassNames(LumoUtility.Background.BASE, LumoUtility.Padding.Horizontal.MEDIUM);

        // Agregar header al AppLayout
        addToNavbar(header);

        // Drawer lateral con enlaces
        VerticalLayout drawerLayout = new VerticalLayout();
        drawerLayout.setPadding(true);
        drawerLayout.setSpacing(true);

        RouterLink dashboardLink = new RouterLink("Dashboard", Main.class);
        RouterLink mothLink = new RouterLink("Month", Month.class);

        drawerLayout.add(dashboardLink,mothLink);

        addToDrawer(drawerLayout);
    }
}
