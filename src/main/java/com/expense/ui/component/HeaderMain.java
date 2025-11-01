package com.expense.ui.component;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.expense.ui.Main;
import com.expense.ui.Month;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
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

        H1 logo = new H1("Expense Manager");
        logo.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

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
        header.expand(logo); // Hace que el logo ocupe el espacio central
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
