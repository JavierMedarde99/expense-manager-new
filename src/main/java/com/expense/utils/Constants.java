package com.expense.utils;

import java.util.List;

import com.vaadin.flow.data.provider.ListDataProvider;

public class Constants {
    public static final ListDataProvider<String> TYPE =
            new ListDataProvider<>(
                    List.of("Fixed", "Variable")
            );
    public static final ListDataProvider<String> SUBTYPE =
            new ListDataProvider<>(
                    List.of("Videogames", "Mtg", "Book", "Subscription", "Peripherals","Clothes", "Other")
            );
}
