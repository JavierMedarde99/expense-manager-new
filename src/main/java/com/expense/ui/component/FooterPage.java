package com.expense.ui.component;

import java.time.LocalDate;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

public class FooterPage extends Footer {

    public FooterPage() {
        Text footerText = new Text("© " + LocalDate.now().getYear() + " Expense Manager");

        Image logo = new Image("images/logo.webp", "Expense Manager Logo");
        logo.setHeight("60px");
        logo.setWidth("60px");

        Image linkedin = new Image("images/linkedin.png", "LinkedIn");
        Anchor linkLinkedin = new Anchor("https://www.linkedin.com/in/javier-medarde-mata-991689181/", linkedin);

        Image github = new Image("images/github.png", "github");
        Anchor linkGithub = new Anchor("https://github.com/JavierMedarde99", github);

        Image email = new Image("images/mail.png", "Email");
        Anchor linkEmail = new Anchor("mailto:javiermedmata99it@gmail.com", email);

        HorizontalLayout layoutLinks = new HorizontalLayout(linkLinkedin, linkGithub, linkEmail);
        layoutLinks.setAlignItems(Alignment.CENTER);
        layoutLinks.setSpacing(true);
        layoutLinks.setWidth("32%");

        HorizontalLayout mainLayout = new HorizontalLayout(footerText, logo, layoutLinks);
        mainLayout.setWidthFull();
        mainLayout.setAlignItems(Alignment.CENTER);
        mainLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        mainLayout.getStyle()
                .set("padding", "10px 40px")
                .set("background-color", "#f5f5f5");

        add(mainLayout);
    }

}
