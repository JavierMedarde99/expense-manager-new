package com.expense.ui;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expense.model.UserDto;
import com.expense.service.UserService;
import com.expense.ui.component.HeaderMain;
import com.expense.ui.component.Cards.UserCard;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("UserInfo")
@RolesAllowed("USER")
@Route("User")
public class User extends Div{
    
    public User(UserService userService) {

        HeaderMain headerMain = new HeaderMain();

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setAlignItems(Alignment.CENTER);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.getUserByUsername(authentication.getName());

        H2 title = new H2("Account Information");
        UserCard userCard = new UserCard(userDto);


        layout.add(title, userCard);

        add(headerMain,layout);
    }
}
