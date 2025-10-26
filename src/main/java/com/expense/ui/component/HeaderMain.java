package com.expense.ui.component;

import com.expense.ui.Main;
import com.nimbusds.jose.Header;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class HeaderMain extends Header{
    
    public HeaderMain() {
        H1 logo = new H1("Expense Manager");

        add();
    }
}
