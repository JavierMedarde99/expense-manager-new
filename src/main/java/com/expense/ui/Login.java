package com.expense.ui;

import com.expense.ui.component.LoginCard;
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
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        VerticalLayout card = new LoginCard();

        H1 title = new H1("Login");

        LoginForm loginForm = new LoginForm();
        loginForm.setAction("login");

        var linkRegister = new RouterLink("registration",Register.class);

        card.add(title, loginForm,linkRegister);

        card.setJustifyContentMode(JustifyContentMode.CENTER);
        card.setAlignItems(Alignment.CENTER);

        add(card);
    }

}
