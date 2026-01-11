package com.expense.ui;

import java.util.List;
import java.util.Map;

import com.expense.ui.component.Cards.LoginCard;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Login")
@Route("login")
public class Login extends FlexLayout implements BeforeEnterObserver{
    
    private LoginForm loginForm = new LoginForm();

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

        loginForm.setAction("login");
        

        var linkRegister = new RouterLink("registration",Register.class);

        card.add(loginForm,linkRegister);

        card.setJustifyContentMode(JustifyContentMode.CENTER);
        card.setAlignItems(Alignment.CENTER);

        add(card);
    }

        @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Map<String, List<String>> params =
                event.getLocation().getQueryParameters().getParameters();

        if (params.containsKey("error")) {
            loginForm.setError(true);
        }
    }

}
