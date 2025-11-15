package com.expense.ui;

import com.expense.service.UserService;
import com.expense.ui.component.Cards.LoginCard;
import com.expense.ui.component.Forms.FormUser;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("registration")
@Route("registration")
public class Register extends FlexLayout{
    
    public Register(UserService userService) {
    setSizeFull();
    setWidth("100%");
    setHeight("100%");
    setJustifyContentMode(JustifyContentMode.CENTER);
    setAlignItems(Alignment.CENTER);

    getStyle().set("background-image", "url('images/background_login.webp')");
    getStyle().set("background-size", "cover");
    getStyle().set("background-repeat", "no-repeat");
    getStyle().set("background-position", "center");

        FormUser formRegister = new FormUser(userService,null);

        VerticalLayout card = new LoginCard();
        card.setPadding(true);
        card.setSpacing(true);
        card.setWidth("400px");
        card.addClassName("register-card");
        card.getStyle()
            .set("background-color", "white")
            .set("border-radius", "12px")
            .set("box-shadow", "0 4px 12px rgba(0, 0, 0, 0.1)")
            .set("padding", "2rem");

        H1 title = new H1("Register");

        card.add(title, formRegister);
        add(card);
    }
}
