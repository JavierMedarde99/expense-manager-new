package com.expense.ui.component.Forms;

import org.springframework.stereotype.Component;

import com.expense.model.UserDto;
import com.expense.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class FormRegister extends FormLayout{
    
    public FormRegister(UserService userService) {
        setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
        );

        TextField userName = new TextField("Username");
        userName.setRequiredIndicatorVisible(true);
        userName.setErrorMessage("name is required");

        TextField email = new TextField("Email");
        email.setRequiredIndicatorVisible(true);
        email.setErrorMessage("email is required");
        
        PasswordField password = new PasswordField("Password");
        password.setRequiredIndicatorVisible(true);
        password.setErrorMessage("password is required");

        NumberField euroField = new NumberField();
        euroField.setLabel("salary");
        Div euroSuffix = new Div();
        euroSuffix.setText("€");
        euroField.setSuffixComponent(euroSuffix);
        euroField.setRequiredIndicatorVisible(true);
        euroField.setErrorMessage("salary is required");

        Button buttonSubmit = new Button("submit");

        Binder<UserDto> binder = new Binder<>(UserDto.class);
        binder.forField(userName)
                .asRequired("Username is required")
                .bind(dto -> dto.getUserName(), (dto, value) -> dto.setUserName(value));

        binder.forField(email)
                .asRequired("Email is required")
                .withValidator(new EmailValidator("Invalid email format"))
                .bind(dto -> dto.getEmail(), (dto, value) -> dto.setEmail(value));

        binder.forField(password)
                .asRequired("Password is required")
                .bind(dto -> dto.getPassword(), (dto, value) -> dto.setPassword(value));

        binder.forField(euroField)
                .asRequired("Salary is required")
                .bind(dto -> dto.getSalary(), (dto, value) -> dto.setSalary(value));

        buttonSubmit.addClickListener(event -> {
            if (binder.validate().isOk()) {
            UserDto userDto = new UserDto(userName.getValue(), email.getValue(), password.getValue(), euroField.getValue());
            if(userService.newUser(userDto)){
                Notification notification =  Notification.show("User registered successfully!", 3000, Notification.Position.BOTTOM_END);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate("login");
            }else{
                Notification notification =  Notification.show("Error registering user.", 3000, Notification.Position.BOTTOM_END);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        }

        });

        add(userName,email,password,euroField,buttonSubmit);

    }
}
