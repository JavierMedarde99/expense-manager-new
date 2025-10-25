package com.expense.ui.component;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LoginCard extends VerticalLayout{
    
    public LoginCard() {
                VerticalLayout card = new VerticalLayout();
        card.setPadding(true);
        card.setSpacing(true);
        card.setWidth("400px");
        card.addClassName("login-card");
        card.getStyle()
            .set("background-color", "white")
            .set("border-radius", "12px")
            .set("box-shadow", "0 4px 12px rgba(0, 0, 0, 0.1)")
            .set("padding", "2rem");
    }
}
