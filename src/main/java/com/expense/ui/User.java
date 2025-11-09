package com.expense.ui;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expense.model.UserDto;
import com.expense.service.UserService;
import com.expense.ui.component.FooterPage;
import com.expense.ui.component.HeaderMain;
import com.expense.ui.component.Cards.UserCard;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("UserInfo")
@RolesAllowed("USER")
@Route("User")
public class User extends VerticalLayout{
    
    public User(UserService userService) {
        setSizeFull(); // ocupa toda la pantalla
        setPadding(false);
        setSpacing(false);

        HeaderMain headerMain = new HeaderMain();

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setAlignItems(Alignment.CENTER);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.getUserByUsername(authentication.getName());

        H2 title = new H2("Account Information");
        UserCard userCard = new UserCard(userDto, userService);
        layout.add(title, userCard);


        FooterPage footer = new FooterPage();


        add(headerMain, layout, footer);

        expand(layout); 
    }
}
