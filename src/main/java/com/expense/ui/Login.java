package com.expense.ui;

import com.expense.ui.component.Cards.LoginCard;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Login")
@Route("login")
public class Login extends FlexLayout{
    

    public Login() {
    setSizeFull();
    setWidth("100%");
    setHeight("100%");
    setJustifyContentMode(JustifyContentMode.CENTER);
    setAlignItems(Alignment.CENTER);

    getStyle().set("background-image", "url('images/background_login.webp')");
    getStyle().set("background-size", "cover");
    getStyle().set("background-repeat", "no-repeat");
    getStyle().set("background-position", "center");

        VerticalLayout card = new LoginCard();

        LoginForm loginForm = new LoginForm();
        loginForm.setAction("login");

        var linkRegister = new RouterLink("registration",Register.class);

        card.add(loginForm,linkRegister);

        card.setJustifyContentMode(JustifyContentMode.CENTER);
        card.setAlignItems(Alignment.CENTER);

        add(card);
    }

}
