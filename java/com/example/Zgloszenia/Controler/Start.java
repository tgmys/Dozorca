/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Zgloszenia.Controler;


import com.example.Zgloszenia.Repozytory.RepozytoryZgloszenie;
import com.example.Zgloszenia.Zgloszenie;

import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.server.ExternalResource;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.BorderStyle;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

import java.util.List;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.swing.JLabel;
/**
 *
 * @author siema
 */

@SpringUI
public class Start extends UI{
    @Autowired
    RepozytoryZgloszenie repozytory;
    @Override
    protected void init(VaadinRequest request)
    {
        VerticalLayout pionowo = new VerticalLayout();
        HorizontalLayout poziomo = new HorizontalLayout();
        
        Label napis = new Label(" Witaj na stronie współdzielni mieszkaniowej przy ulicy Kolorowej");
        Label napis2 = new Label("Jako kto chcesz się zalogować ?");
        Link dozorcaLink=new Link("DOZORCA",new ExternalResource("dozorca"));
        //dozorcaLink.setTargetHeight(1000);
        //dozorcaLink.setTargetWidth(1000);
        Link lokator=new Link("LOKATOR",new ExternalResource("lokator"));
        
        poziomo.addComponents(lokator,dozorcaLink);
        pionowo.addComponents(napis, napis2 , poziomo);
        setContent(pionowo);
    }
}