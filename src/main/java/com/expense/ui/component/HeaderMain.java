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
import com.vaadin.flow.theme.lumo.LumoUtility;

import jakarta.servlet.http.HttpServletRequest;

@Component
@UIScope
public class HeaderMain extends AppLayout{
    
    public HeaderMain() {

        // hamburger menu
        DrawerToggle toggle = new DrawerToggle();
        

        Image logo = new Image("images/logo.webp", "Expense Manager Logo");
        logo.setHeight("60px");
        logo.setWidth("60px");

        Button logoutButton = new Button("Logout", e -> {
            try {
                // Llamada al logout estándar de Servlet (Spring Security lo maneja)
                HttpServletRequest request = VaadinServletRequest.getCurrent().getHttpServletRequest();
                request.logout();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // Limpiar contexto de seguridad y redirigir al login
            SecurityContextHolder.clearContext();
        });
        logoutButton.addClassNames(LumoUtility.Margin.End.MEDIUM);

        // Header
        HorizontalLayout header = new HorizontalLayout(toggle, logo, logoutButton);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        header.addClassNames(LumoUtility.Background.BASE, LumoUtility.Padding.Horizontal.MEDIUM);
        header.getStyle()
            .set("background-color", "#f5f5f5"); // color de fondo del header

        // Agregar header al AppLayout
        addToNavbar(header);

        // Drawer lateral con enlaces
        VerticalLayout drawerLayout = new VerticalLayout();
        drawerLayout.setPadding(true);
        drawerLayout.setSpacing(true);
        drawerLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        drawerLayout.getStyle().setBackgroundColor("#f5f5f5");

        RouterLink dashboardLink = new RouterLink("Dashboard", Main.class);
        RouterLink mothLink = new RouterLink("Month", Month.class);
        RouterLink userLink = new RouterLink("User", User.class);
        RouterLink yearLink = new RouterLink("Year", Year.class);

        Image logoDrawer = new Image("images/logo.webp", "Expense Manager Logo");
        logoDrawer.setHeight("80px");
        logoDrawer.setWidth("80px");

        drawerLayout.add(logoDrawer,dashboardLink,mothLink,userLink,yearLink);

        addToDrawer(drawerLayout);
    }
}
