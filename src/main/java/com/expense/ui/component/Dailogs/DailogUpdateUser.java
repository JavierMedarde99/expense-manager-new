package com.expense.ui.component.Dailogs;

import com.expense.model.UserDto;
import com.expense.service.UserService;
import com.expense.ui.component.Forms.FormUser;
import com.vaadin.flow.component.dialog.Dialog;

public class DailogUpdateUser extends Dialog{
    
    public DailogUpdateUser(UserService userService, UserDto userDto) {
        setHeaderTitle("Update Account");
        FormUser formUser = new FormUser(userService, userDto);
        add(formUser);
    }
}
