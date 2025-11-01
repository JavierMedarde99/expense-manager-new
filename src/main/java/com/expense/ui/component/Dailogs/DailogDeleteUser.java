package com.expense.ui.component.Dailogs;

import com.expense.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.VaadinServletRequest;

import jakarta.servlet.http.HttpServletRequest;

public class DailogDeleteUser extends Dialog{
    
    public DailogDeleteUser(UserService userService, String username) {
        setHeaderTitle("Delete User");

        Text text = new Text("Are you sure you want to delete your account? This action cannot be undone.");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull(); 
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN); 
        layout.setPadding(false);
        layout.setSpacing(false);

        Button btnConfirm = new Button("Confirm");
        btnConfirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
        ButtonVariant.LUMO_ERROR);
        btnConfirm.addClickListener(e ->{
            userService.deleteUser(username);
            try {
                // Llamada al logout estándar de Servlet (Spring Security lo maneja)
                HttpServletRequest request = VaadinServletRequest.getCurrent().getHttpServletRequest();
                request.logout();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            close();
        });

        Button btnCancel = new Button("Cancel", e -> close());

        layout.add(btnConfirm,btnCancel);

        add(text,layout);
    }
}
