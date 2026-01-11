package com.expense.ui.component.Forms;

import org.springframework.stereotype.Component;

import com.expense.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class FormForgotPassword extends FormLayout {

    public FormForgotPassword(UserService userService) {

        TextField username = new TextField("Username");
        username.setRequiredIndicatorVisible(true);
        username.setErrorMessage("Username is required");

        Button btnSubmit = new Button("Submit");
        Button btnChangePassword = new Button("Change password");
        btnChangePassword.setVisible(false);

        TextField password = new TextField("New password");
        password.setRequiredIndicatorVisible(true);
        password.setErrorMessage("Password is required");
        password.setVisible(false);

        add(username, btnSubmit, password, btnChangePassword);

        btnSubmit.addClickListener(event -> {

            try {
                userService.getUserByUsername(username.getValue());
                username.setReadOnly(true);

                // Ocultar botón submit
                btnSubmit.setVisible(false);

                // Mostrar campos nuevos
                password.setVisible(true);
                btnChangePassword.setVisible(true);
            } catch (Exception e) {
                Notification notification = Notification.show("User not found!", 3000,
                        Notification.Position.BOTTOM_END);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }

        });

        btnChangePassword.addClickListener(event -> {
            try {
                userService.changePassword(username.getValue(), password.getValue());
            } catch (Exception e) {
                Notification notification = Notification.show("User not found!", 3000,
                        Notification.Position.BOTTOM_END);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }

            Notification notification = Notification.show("User changed password successfully!", 3000,
                    Notification.Position.BOTTOM_END);
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            UI.getCurrent().navigate("login");
        });
    }
}
