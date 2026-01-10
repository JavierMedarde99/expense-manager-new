package com.expense.ui.component;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.expense.ui.Main;
import com.expense.ui.Month;
import com.expense.ui.User;
import com.expense.ui.Year;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.spring.annotation.UIScope;

import jakarta.servlet.http.HttpServletRequest;

@Component
@UIScope
public class HeaderMain extends AppLayout {

    public HeaderMain() {

        DrawerToggle toggle = new DrawerToggle();

        Image logo = new Image("images/logo.webp", "Expense Manager Logo");
        logo.setHeight("100px");
        logo.setWidth("100px");

        Button logoutButton = new Button("Logout", e -> {
            try {
                HttpServletRequest request =
                        VaadinServletRequest.getCurrent().getHttpServletRequest();
                request.logout();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            SecurityContextHolder.clearContext();
        });

        // 🔹 Navbar (toggle + contenido)
        HorizontalLayout header = new HorizontalLayout(toggle, logo, logoutButton);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        header.getStyle().set("background-color", "#f5f5f5");

        addToNavbar(header);

        Image logoMenu = new Image("images/logo.webp", "Expense Manager Logo");
        logoMenu.setHeight("60px");
        logoMenu.setWidth("60px");

        // 🔹 Drawer
        VerticalLayout drawerLayout = new VerticalLayout(logoMenu,
                new RouterLink("Dashboard", Main.class),
                new RouterLink("Month", Month.class),
                new RouterLink("User", User.class),
                new RouterLink("Year", Year.class)
        );
        drawerLayout.setPadding(true);
        drawerLayout.setSpacing(true);
        drawerLayout.setSizeFull();

        addToDrawer(drawerLayout);

        // 🔹 Para que EMPUJE el contenido
        setPrimarySection(Section.DRAWER);
        setDrawerOpened(false);
    }
}

