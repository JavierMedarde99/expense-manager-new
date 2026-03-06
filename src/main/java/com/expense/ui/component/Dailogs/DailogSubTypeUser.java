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

    public DailogSubTypeUser(Long userId, SubTypesService subTypeService, List<SubTypesDto> listSubTypes) {

        List<SubTypesDto> listSubTypesNew;

        if (listSubTypes != null) {
            listSubTypesNew = listSubTypes;
        } else {
            listSubTypesNew = new ArrayList<>();
        }

        setHeaderTitle("custom subtypes");

        HorizontalLayout inputs = new HorizontalLayout();
        inputs.setAlignItems(Alignment.CENTER);

        TextField inputText = new TextField("subtype name");
        Input colorPicker = new Input();
        colorPicker.setType("color");

        Button button = new Button("send subType");
        Button saveSubTypes = new Button("save SubType");

        saveSubTypes.addClickListener(event -> {
            if (listSubTypesNew.isEmpty()) {
                Notification notification = Notification.show("Please add at least one subType", 3000,
                        Notification.Position.BOTTOM_END);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                subTypeService.saveAllSubtype(userId, listSubTypesNew);
                Notification notification = Notification.show(
                        listSubTypes != null ? "update subtyepe succesfully" : "User registered successfully!", 3000,
                        Notification.Position.BOTTOM_END);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                close();
                if (listSubTypes == null){
                    UI.getCurrent().navigate("login");
                }

            }
        });

        VerticalLayout listSubtypesVertical = new VerticalLayout();

        if (listSubTypes != null) {
            for (SubTypesDto subTypesDto : listSubTypesNew) {
                HorizontalLayout subtypesDiv = new HorizontalLayout();
                Div cuadrado = new Div();
                cuadrado.setWidth("20px");
                cuadrado.setHeight("20px");
                cuadrado.getStyle().set("background-color",
                        subTypesDto.getColor().isBlank() ? "black" : subTypesDto.getColor());

                Icon iconDelete = new Icon(VaadinIcon.ARROWS_CROSS);
                iconDelete.setColor("#FF0000");
                iconDelete.addClickListener(event2 -> {
                    if (!subTypeService.deleteSubtype(userId, subTypesDto)) {
                        Notification notification = Notification.show("It could not be removed, please check if there are any associated costs", 3000,
                                Notification.Position.BOTTOM_END);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    }else{
                        listSubTypesNew.remove(subTypesDto);
                subtypesDiv.removeAll();
                    }
                });

                subtypesDiv.add(cuadrado, new Text(subTypesDto.getName()), iconDelete);
                listSubtypesVertical.add(subtypesDiv);
            }

        }

        button.addClickListener(event -> {
            HorizontalLayout subtypesDiv = new HorizontalLayout();
            SubTypesDto subTypesDto = new SubTypesDto(inputText.getValue(),
                    colorPicker.getValue().isBlank() ? "#000000" : colorPicker.getValue());
            listSubTypesNew.add(subTypesDto);
            Div cuadrado = new Div();
            cuadrado.setWidth("20px");
            cuadrado.setHeight("20px");
            cuadrado.getStyle().set("background-color",
                    subTypesDto.getColor().isBlank() ? "black" : subTypesDto.getColor());
            Icon iconDelete = new Icon(VaadinIcon.ARROWS_CROSS);
            iconDelete.setColor("#FF0000");
            iconDelete.addClickListener(event2 -> {
                listSubTypesNew.remove(subTypesDto);
                subtypesDiv.removeAll();
            });
            subtypesDiv.add(cuadrado, new Text(subTypesDto.getName()), iconDelete);
            listSubtypesVertical.add(subtypesDiv);
        });

        inputs.add(inputText, colorPicker, button);
        add(inputs, listSubtypesVertical, saveSubTypes);
    }
}
