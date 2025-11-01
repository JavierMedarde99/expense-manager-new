package com.expense.ui.component.Cards;

import com.expense.model.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.html.Paragraph;

public class UserCard extends Card{
    
    public UserCard(UserDto userDto) {
        Paragraph username = new Paragraph("Username: " + userDto.getUserName());
        Paragraph email = new Paragraph("Email: " + userDto.getEmail());
        Paragraph password = new Paragraph("Password: *************");
        Paragraph salary = new Paragraph("Salary: " + userDto.getSalary());
        
        setTitle("Personal Infomation");
        add(username, email,password, salary);

        Button editButton = new Button("Edit Information");

        Button deleteButton = new Button("Delete Account");

        addToFooter(editButton, deleteButton);
    }
}
