package com.expense.ui.component.Dailogs;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.UI;

import java.util.ArrayList;
import java.util.List;

import com.expense.model.SubTypesDto;
import com.expense.service.SubTypesService;

public class DailogSubTypeUser extends Dialog {

    
    public DailogSubTypeUser(Long userId, SubTypesService subTypeService){

        List<SubTypesDto> listSubTypes = new ArrayList<>();

        setHeaderTitle("custom subtypes");

        HorizontalLayout inputs = new HorizontalLayout();
        inputs.setAlignItems(Alignment.CENTER);

        TextField inputText = new TextField("subtype name");
        Input colorPicker = new Input();
        colorPicker.setType("color");

        Button button = new Button("send subType");
        Button saveSubTypes = new Button("save SubType");

        saveSubTypes.addClickListener(event->{
            if(listSubTypes.isEmpty()){
                Notification notification = Notification.show("Please add at least one subType", 3000,
                                Notification.Position.BOTTOM_END);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else{
                subTypeService.saveAllSubtype(userId, listSubTypes);
                Notification notification = Notification.show("User registered successfully!", 3000,
                                Notification.Position.BOTTOM_END);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        close();
                        UI.getCurrent().navigate("login");
            }
        });

        VerticalLayout listSubtypes = new VerticalLayout();

        button.addClickListener(event ->{
            HorizontalLayout subtypes = new HorizontalLayout();
            SubTypesDto subTypesDto = new SubTypesDto(inputText.getValue(), colorPicker.getValue().isBlank() ? "#000000": colorPicker.getValue());
            listSubTypes.add(subTypesDto);
            Div cuadrado = new Div();
            cuadrado.setWidth("20px");
            cuadrado.setHeight("20px");
            cuadrado.getStyle().set("background-color", subTypesDto.getColor().isBlank() ? "black": subTypesDto.getColor());
            Icon iconDelete = new Icon(VaadinIcon.ARROWS_CROSS);
            iconDelete.setColor("#FF0000");
            iconDelete.addClickListener(event2->{
                listSubTypes.remove(subTypesDto);
                subtypes.removeAll();
            });
            subtypes.add(cuadrado,new Text(subTypesDto.getName()),iconDelete);
            listSubtypes.add(subtypes);
        });

        inputs.add(inputText,colorPicker,button);
        add(inputs,listSubtypes,saveSubTypes);
    }
}
