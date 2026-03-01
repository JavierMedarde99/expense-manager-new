package com.expense.ui.component.Cards;

import com.expense.model.UserDto;
import com.expense.service.SubTypesService;
import com.expense.service.UserService;
import com.expense.ui.component.Dailogs.DailogDeleteUser;
import com.expense.ui.component.Dailogs.DailogSubTypeUser;
import com.expense.ui.component.Dailogs.DailogUpdateUser;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class UserCard extends Card {

    public UserCard(UserDto userDto, UserService userService,SubTypesService subTypeService) {
        Paragraph username = new Paragraph("Username: " + userDto.getUserName());
        Paragraph email = new Paragraph("Email: " + userDto.getEmail());
        Paragraph password = new Paragraph("Password: *************");
        Paragraph salary = new Paragraph("Salary: " + userDto.getSalary());
        HorizontalLayout subTypes = new HorizontalLayout();
        Text textSubType = new Text("Subtypes: ");
        DailogSubTypeUser dialog = new DailogSubTypeUser(userService.getUserId(userDto.getUserName()), subTypeService, subTypeService.getAllSubtype(userDto.getUserName()));
        Button updateSubtype = new Button("update subtyoe");
        updateSubtype.addClickListener(event->dialog.open());
        subTypes.add(textSubType,updateSubtype);

        setTitle("Personal Infomation");
        add(username, email, password, salary,subTypes);

        Button editButton = new Button("Edit Information");
        editButton.addClickListener(e -> {
            DailogUpdateUser dailogUpdateUser = new DailogUpdateUser(userService, userDto);
            dailogUpdateUser.open();
        });

        Button deleteButton = new Button("Delete Account");
        deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_ERROR);
        deleteButton.addClickListener(e -> {
            DailogDeleteUser dailogDeleteUser = new DailogDeleteUser(userService, userDto.getUserName());
            dailogDeleteUser.open();
        });

        addToFooter(editButton, deleteButton);
    }
}
